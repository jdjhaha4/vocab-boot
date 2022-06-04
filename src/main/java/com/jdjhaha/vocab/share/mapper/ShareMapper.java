package com.jdjhaha.vocab.share.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ShareMapper {
	List<HashMap<Object, Object>> selectMyGroupList(HashMap<Object, Object> paramMap);
	List<HashMap<Object, Object>> selectOthersGroupList(HashMap<Object, Object> paramMap);
	HashMap<Object, Object> selectOthersGroupData(HashMap<Object, Object> paramMap);
	List<HashMap<Object, Object>> selectOthersVocabList(HashMap<Object, Object> paramMap);
}
