package com.jdjhaha.vocab.vocab.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdjhaha.vocab.vocab.mapper.VocabQuestionResultHistoryMapper;
import com.jdjhaha.vocab.vocab.service.VocabQuestionResultHistoryService;
import com.jdjhaha.vocab.vocab.vo.VocabQuestionResultHistoryVO;

@Service
public class VocabQuestionResultHistoryServiceImpl implements VocabQuestionResultHistoryService {
	
	@Autowired
	private VocabQuestionResultHistoryMapper vocabQuestionResultHistoryMapper;
	
	@Override
	public List<HashMap<Object, Object>> selectData(HashMap<Object, Object> paramMap) {
		return vocabQuestionResultHistoryMapper.selectData(paramMap);
	}

	@Override
	public int insertData(HashMap<Object, Object> paramMap) {
		return vocabQuestionResultHistoryMapper.insertData(paramMap);
	}
	
	@Override
	public int insertData2(VocabQuestionResultHistoryVO vocabQuestionResultHistoryVO) {
		return vocabQuestionResultHistoryMapper.insertData2(vocabQuestionResultHistoryVO);
	}

	@Override
	public int deleteData(HashMap<Object, Object> paramMap) {
		return vocabQuestionResultHistoryMapper.deleteData(paramMap);
	}

}
