package com.jdjhaha.vocab.main.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MainMapper {
	List<HashMap<Object, Object>> selectMyVocabDataList(HashMap<Object, Object> paramMap);
}
