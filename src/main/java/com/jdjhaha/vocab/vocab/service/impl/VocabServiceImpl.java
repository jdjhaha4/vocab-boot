package com.jdjhaha.vocab.vocab.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdjhaha.vocab.vocab.mapper.VocabMapper;
import com.jdjhaha.vocab.vocab.service.VocabService;
import com.jdjhaha.vocab.vocab.vo.VocabVO;

@Service
public class VocabServiceImpl implements VocabService {
	
	@Autowired
	private VocabMapper vocabMapper;
	
	@Override
	public List<HashMap<Object, Object>> selectData(HashMap<Object, Object> paramMap) {
		return vocabMapper.selectData(paramMap);
	}

	@Override
	public int insertData(VocabVO vocab) {
		return vocabMapper.insertData2(vocab);
	}

	@Override
	public int insertData(HashMap<Object, Object> paramMap) {
		return vocabMapper.insertData(paramMap);
	}

	@Override
	public int deleteData(HashMap<Object, Object> paramMap) {
		return vocabMapper.deleteData(paramMap);
	}

}
