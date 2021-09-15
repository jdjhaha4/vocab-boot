package com.jdjhaha.vocab.vocab.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdjhaha.vocab.vocab.mapper.VocabQuestionResultMapper;
import com.jdjhaha.vocab.vocab.service.VocabQuestionResultService;
import com.jdjhaha.vocab.vocab.vo.VocabQuestionResultVO;

@Service
public class VocabQuestionResultServiceImpl implements VocabQuestionResultService {
	
	@Autowired
	private VocabQuestionResultMapper vocabQuestionResultMapper;
	
	@Override
	public List<HashMap<Object, Object>> selectData(HashMap<Object, Object> paramMap) {
		return vocabQuestionResultMapper.selectData(paramMap);
	}

	@Override
	public int insertData(HashMap<Object, Object> paramMap) {
		return vocabQuestionResultMapper.insertData(paramMap);
	}
	

	@Override
	public int insertData(VocabQuestionResultVO vocabQuestionResultVO) {
		return vocabQuestionResultMapper.insertData2(vocabQuestionResultVO);
	}

	@Override
	public int updateData(HashMap<Object, Object> paramMap) {
		return vocabQuestionResultMapper.updateData(paramMap);
	}

	@Override
	public int deleteData(HashMap<Object, Object> paramMap) {
		return vocabQuestionResultMapper.deleteData(paramMap);
	}

}
