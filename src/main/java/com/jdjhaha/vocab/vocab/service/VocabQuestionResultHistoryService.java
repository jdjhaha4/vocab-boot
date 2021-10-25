package com.jdjhaha.vocab.vocab.service;

import java.util.HashMap;
import java.util.List;

import com.jdjhaha.vocab.vocab.vo.VocabQuestionResultHistoryVO;

public interface VocabQuestionResultHistoryService {
	List<HashMap<Object, Object>> selectData(HashMap<Object, Object> paramMap);

	int insertData(HashMap<Object, Object> paramMap);
	int insertData2(VocabQuestionResultHistoryVO vocabQuestionResultHistoryVO);
	int deleteData(HashMap<Object, Object> paramMap);
}
