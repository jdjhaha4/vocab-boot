package com.jdjhaha.vocab.login.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdjhaha.vocab.login.mapper.LoginUserMapper;
import com.jdjhaha.vocab.login.service.LoginUserService;

@Service
public class LoginUserServiceImpl implements LoginUserService {
	
	@Autowired
	private LoginUserMapper loginUserMapper;
	
	@Override
	public HashMap<Object, Object> selectData(String id) {
		
		HashMap<Object, Object> vo = new HashMap<Object, Object>();
		vo.put("username", id);
		return loginUserMapper.selectData(vo );
	}

}
