package com.jdjhaha.vocab.vocab.service;

import java.util.HashMap;
import java.util.List;

import com.jdjhaha.vocab.vocab.vo.VocabVO;

public interface VocabService {
	List<HashMap<Object, Object>> selectData(HashMap<Object, Object> paramMap);

	int insertData(HashMap<Object, Object> paramMap);
	int insertData(VocabVO vocab);
	int updateData(VocabVO vocab);
	int deleteData(HashMap<Object, Object> paramMap);
}
