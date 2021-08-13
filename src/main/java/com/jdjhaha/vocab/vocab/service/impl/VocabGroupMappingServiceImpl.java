package com.jdjhaha.vocab.vocab.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdjhaha.vocab.vocab.mapper.VocabGroupMappingMapper;
import com.jdjhaha.vocab.vocab.service.VocabGroupMappingService;

@Service
public class VocabGroupMappingServiceImpl implements VocabGroupMappingService {
	
	@Autowired
	private VocabGroupMappingMapper vocabGroupMappingMapper;
	
	@Override
	public List<HashMap<Object, Object>> selectData(HashMap<Object, Object> paramMap) {
		return vocabGroupMappingMapper.selectData(paramMap);
	}

	@Override
	public int insertData(HashMap<Object, Object> paramMap) {
		return vocabGroupMappingMapper.insertData(paramMap);
	}

	@Override
	public int deleteData(HashMap<Object, Object> paramMap) {
		return vocabGroupMappingMapper.deleteData(paramMap);
	}

}
