package com.jdjhaha.vocab.login.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jdjhaha.vocab.login.jwt.JwtRequest;
import com.jdjhaha.vocab.login.jwt.JwtResponse;
import com.jdjhaha.vocab.login.jwt.JwtTokenUtil;
import com.jdjhaha.vocab.login.service.LoginUserDetailsService;
import com.jdjhaha.vocab.login.service.LoginUserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://192.168.215.54:3000","https://quiet-rattlesnake-14.loca.lt"})
public class LoginController {
	@Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private LoginUserDetailsService userDetailsService;
    
    @Autowired
    private LoginUserService loginUserService;
    
    @Autowired
	PasswordEncoder passwordEncoder;
    
    @GetMapping("/hello")
    public String hello() {
    	return "Hello";
    }
    
	@GetMapping("/api/auth/check")
	public Map<Object, Object> authCheck(Principal principal) {
		Map<Object, Object> resultMap = new HashMap<Object, Object>();
		HashMap<Object, Object> userData = loginUserService.selectData(principal.getName());
		resultMap.put("id", userData.get("username"));
		resultMap.put("username", userData.get("nickname"));
		return resultMap;
	}
	
	@RequestMapping(value = "/api/auth/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getId(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService
            .loadUserByUsername(authenticationRequest.getId());

        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }
	
	@RequestMapping(value = "/api/auth/logout", method = RequestMethod.POST)
    public void logout(Principal principal) throws Exception {
    }
    
	@PostMapping("/api/auth/register")
	public ResponseEntity<?> register(@RequestBody String data) {
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		JSONObject obj = new JSONObject(data);
		
		HashMap<Object, Object> paramMap = new HashMap<>();
		paramMap.put("username", obj.get("id"));
		paramMap.put("nickname", obj.get("nickname"));
		paramMap.put("password", passwordEncoder.encode(obj.get("password").toString()));
		
		HashMap<Object, Object> idDupleCheck = loginUserService.selectData(obj.getString("id"));
		HashMap<Object, Object> nicknameDupleCheck = loginUserService.selectDataByNickname(obj.getString("nickname"));
		if(idDupleCheck != null || nicknameDupleCheck != null) {
			resultMap.put("isDuple", "아이디 또는 닉네임이 중복되었습니다");
			return ResponseEntity.ok(resultMap);
		}
		
		int resultCnt = loginUserService.insertData(paramMap);
		UserDetails userDetails= null;
		String token = null;
		if(resultCnt == 1) {
			userDetails = userDetailsService.loadUserByUsername(obj.getString("id"));
			token = jwtTokenUtil.generateToken(userDetails);
		}

		return ResponseEntity.ok(new JwtResponse(token));
	}
	@PostMapping("/api/auth/idDupleCheck")
	public HashMap<Object, Object> idDupleCheck(@RequestBody String data) {
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		JSONObject obj = new JSONObject(data);
		HashMap<Object, Object> user = loginUserService.selectData(obj.getString("id"));
		resultMap.put("id", obj.getString("id"));
		if(user != null) {
			resultMap.put("available", false);
		}else {
			resultMap.put("available", true);
		}
		
		return resultMap;
	}
	@PostMapping("/api/auth/nicknameDupleCheck")
	public HashMap<Object, Object> nicknameDupleCheck(@RequestBody String data) {
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		JSONObject obj = new JSONObject(data);
		HashMap<Object, Object> user = loginUserService.selectDataByNickname(obj.getString("nickname"));
		resultMap.put("nickname", obj.getString("nickname"));
		if(user != null) {
			resultMap.put("available", false);
		}else {
			resultMap.put("available", true);
		}
		
		return resultMap;
	}

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
