package com.jdjhaha.vocab.login.service;

import java.util.HashMap;

public interface LoginUserService {
	HashMap<Object, Object> selectData(String id);
	int insertData(HashMap<Object,Object> vo);
}
