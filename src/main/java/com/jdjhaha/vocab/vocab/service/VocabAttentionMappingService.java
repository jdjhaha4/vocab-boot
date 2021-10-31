package com.jdjhaha.vocab.vocab.service;

import java.util.HashMap;
import java.util.List;

public interface VocabAttentionMappingService {
	List<HashMap<Object, Object>> selectData(HashMap<Object, Object> paramMap);
	int insertData(HashMap<Object, Object> paramMap);
}
