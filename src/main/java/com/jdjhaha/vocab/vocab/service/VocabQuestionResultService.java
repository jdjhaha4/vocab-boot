package com.jdjhaha.vocab.vocab.service;

import java.util.HashMap;
import java.util.List;

import com.jdjhaha.vocab.vocab.vo.VocabQuestionResultVO;

public interface VocabQuestionResultService {
	List<HashMap<Object, Object>> selectData(HashMap<Object, Object> paramMap);

	int insertData(HashMap<Object, Object> paramMap);
	int insertData(VocabQuestionResultVO vocabQuestionResultVO);
	int updateData(HashMap<Object, Object> paramMap);
	int deleteData(HashMap<Object, Object> paramMap);

	List<HashMap<Object, Object>> selectGroupByData(HashMap<Object, Object> paramMap);
}
