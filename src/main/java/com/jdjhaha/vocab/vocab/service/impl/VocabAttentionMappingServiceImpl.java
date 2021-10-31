package com.jdjhaha.vocab.vocab.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdjhaha.vocab.vocab.mapper.VocabAttentionMappingMapper;
import com.jdjhaha.vocab.vocab.service.VocabAttentionMappingService;

@Service
public class VocabAttentionMappingServiceImpl implements VocabAttentionMappingService {
	
	@Autowired
	private VocabAttentionMappingMapper vocabAttentionMappingMapper;

	@Override
	public List<HashMap<Object, Object>> selectData(HashMap<Object, Object> paramMap) {
		return vocabAttentionMappingMapper.selectData(paramMap);
	}

	@Override
	public int insertData(HashMap<Object, Object> paramMap) {
		return vocabAttentionMappingMapper.insertData(paramMap);
	}

	
}
