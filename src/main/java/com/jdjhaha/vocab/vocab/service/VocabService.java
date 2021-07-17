package com.jdjhaha.vocab.vocab.service;

import java.util.HashMap;
import java.util.List;

public interface VocabService {
	List<HashMap<Object, Object>> selectData();

	int insertData(HashMap<Object, Object> paramMap);
}
