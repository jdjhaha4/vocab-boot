package com.jdjhaha.vocab.main.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdjhaha.vocab.main.mapper.MainMapper;
import com.jdjhaha.vocab.main.service.MainService;

@Service
public class MainServiceImpl implements MainService {
	
	@Autowired
	private MainMapper mainMapper;
	
	@Override
	public List<HashMap<Object, Object>> selectMyVocabDataList(HashMap<Object, Object> paramMap) {
		
		return mainMapper.selectMyVocabDataList(paramMap);
	}
	
}
