package com.jdjhaha.vocab.vocab.service;

import java.util.HashMap;
import java.util.List;

import com.jdjhaha.vocab.vocab.vo.VocabVO;

public interface VocabDictionaryService {
	List<HashMap<Object, Object>> requestData(HashMap<String, String> paramMap);
}
