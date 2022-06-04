package com.jdjhaha.vocab.share.service;

import java.util.HashMap;
import java.util.List;

public interface ShareService {
	List<HashMap<Object, Object>> selectMyGroupList(HashMap<Object, Object> paramMap);
	List<HashMap<Object, Object>> selectOthersGroupList(HashMap<Object, Object> paramMap);
	HashMap<Object, Object> selectOthersGroupData(HashMap<Object, Object> paramMap);
	List<HashMap<Object, Object>> selectOthersVocabList(HashMap<Object, Object> paramMap);
}
