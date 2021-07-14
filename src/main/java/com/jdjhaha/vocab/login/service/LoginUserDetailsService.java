package com.jdjhaha.vocab.login.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jdjhaha.vocab.login.mapper.LoginUserMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LoginUserDetailsService implements UserDetailsService {
	@Autowired
	LoginUserMapper loginUserMapper; 
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		map.put("username", username);
		HashMap<Object, Object> user = loginUserMapper.selectData(map );
		if (user != null ) {
			String selectedUsername = user.get("username").toString();
			String selectedPassword = user.get("password").toString();
            return new User(selectedUsername, selectedPassword,
                new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with emp_no: " + username);
        }
	}

}
