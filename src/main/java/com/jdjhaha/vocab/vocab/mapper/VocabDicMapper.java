package com.jdjhaha.vocab.vocab.mapper;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface VocabDicMapper {
	int insertData(HashMap<Object, Object> paramMap);
}
