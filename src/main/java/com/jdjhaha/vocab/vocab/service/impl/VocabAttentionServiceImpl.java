package com.jdjhaha.vocab.vocab.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdjhaha.vocab.vocab.mapper.VocabAttentionMapper;
import com.jdjhaha.vocab.vocab.mapper.VocabAttentionMappingMapper;
import com.jdjhaha.vocab.vocab.service.VocabAttentionService;

@Service
public class VocabAttentionServiceImpl implements VocabAttentionService {
	
	@Autowired
	private VocabAttentionMapper vocabAttentionMapper;
	
	@Autowired
	private VocabAttentionMappingMapper vocabAttentionMappingMapper;

	@Override
	public List<HashMap<Object, Object>> selectData(HashMap<Object, Object> paramMap) {
		return vocabAttentionMapper.selectData(paramMap);
	}

	@Override
	public HashMap<Object, Object> selectOneData(HashMap<Object, Object> paramMap) {
		List<HashMap<Object, Object>> resultList = vocabAttentionMapper.selectData(paramMap);
		if(resultList != null && resultList.size() > 0 ) {
			return resultList.get(0);
		}else {
			return null;
		}
	}

	@Override
	public int insertData(HashMap<Object, Object> paramMap) {
		HashMap<Object, Object> resultData = selectOneData(paramMap);
//		vocab_id,
//		vocab_question_result_id,
//		vocab_question_result_history_id
		vocabAttentionMappingMapper.insertData(paramMap);
		if(resultData != null) {
			paramMap.put("wrong_count", ((int)resultData.get("wrong_count")+1));
			return updateData(paramMap);
		}else {
			return vocabAttentionMapper.insertData(paramMap);
		}
	}

	@Override
	public int updateData(HashMap<Object, Object> paramMap) {
		return vocabAttentionMapper.updateData(paramMap);
	}
	
	
}
