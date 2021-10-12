package com.jdjhaha.vocab.vocab.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.jdjhaha.vocab.vocab.vo.VocabVO;

@Repository
@Mapper
public interface VocabAttentionMapper {
	List<HashMap<Object, Object>> selectData(HashMap<Object, Object> paramMap);
	
	int insertData2(VocabVO vocab);
	int insertData(HashMap<Object, Object> paramMap);
	int deleteData(HashMap<Object, Object> map);
}
