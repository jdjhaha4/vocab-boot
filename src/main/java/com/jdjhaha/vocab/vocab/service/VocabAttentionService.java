package com.jdjhaha.vocab.vocab.service;

import java.util.HashMap;
import java.util.List;

public interface VocabAttentionService {
	List<HashMap<Object, Object>> selectData(HashMap<Object, Object> paramMap);
	HashMap<Object, Object> selectOneData(HashMap<Object, Object> paramMap);
	
	int insertData(HashMap<Object, Object> paramMap);
	int updateData(HashMap<Object, Object> paramMap);
}
