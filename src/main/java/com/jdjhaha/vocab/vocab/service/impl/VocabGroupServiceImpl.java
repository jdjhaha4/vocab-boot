package com.jdjhaha.vocab.vocab.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdjhaha.vocab.vocab.mapper.VocabGroupMapper;
import com.jdjhaha.vocab.vocab.mapper.VocabGroupMappingMapper;
import com.jdjhaha.vocab.vocab.service.VocabGroupService;

@Service
public class VocabGroupServiceImpl implements VocabGroupService {
	
	@Autowired
	private VocabGroupMapper vocabGroupMapper;
	
	@Autowired
	private VocabGroupMappingMapper vocabGroupMappingMapper;
	
	@Override
	public List<HashMap<Object, Object>> selectData(HashMap<Object, Object> paramMap) {
		return vocabGroupMapper.selectData(paramMap);
	}

	@Override
	public int insertData(HashMap<Object, Object> paramMap) {
		return vocabGroupMapper.insertData(paramMap);
	}

	@Override
	public int deleteData(HashMap<Object, Object> paramMap) {
		vocabGroupMappingMapper.deleteDataByGroupCode(paramMap);
		return vocabGroupMapper.deleteData(paramMap);
	}

}
