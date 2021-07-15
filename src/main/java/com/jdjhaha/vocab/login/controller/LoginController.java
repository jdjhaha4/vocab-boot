package com.jdjhaha.vocab.login.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
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
@CrossOrigin(origins = {"http://localhost:3000", "http://192.168.44.120:3000"})
public class LoginController {
	@Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private LoginUserDetailsService userDetailsService;
    
    @Autowired
    private LoginUserService loginUserService;
    
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
	public void register(@RequestBody String data) {
		
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
