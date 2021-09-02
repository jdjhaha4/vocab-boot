package com.jdjhaha.vocab.vocab.service;

import java.util.HashMap;
import java.util.List;

public interface VocabGroupService {
	List<HashMap<Object, Object>> selectData(HashMap<Object, Object> paramMap);
	HashMap<Object, Object> selectOneData(HashMap<Object, Object> paramMap);

	int insertData(HashMap<Object, Object> paramMap);
	int deleteData(HashMap<Object, Object> paramMap);
}
