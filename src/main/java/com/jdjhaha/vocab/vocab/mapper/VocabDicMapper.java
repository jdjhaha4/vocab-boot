package com.jdjhaha.vocab.vocab.mapper;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface VocabDicMapper {
	HashMap<Object, Object> selectData(HashMap<String, String> paramMap);
	int insertData(HashMap<Object, Object> paramMap);
}
