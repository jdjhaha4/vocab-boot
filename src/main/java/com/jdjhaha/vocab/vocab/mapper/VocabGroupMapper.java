package com.jdjhaha.vocab.vocab.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface VocabGroupMapper {
	List<HashMap<Object, Object>> selectData(HashMap<Object, Object> paramMap);
	List<HashMap<Object, Object>> selectOthersReleaseData(HashMap<Object, Object> paramMap);

	int insertData(HashMap<Object, Object> paramMap);
	int updateData(HashMap<Object, Object> paramMap);
	int deleteData(HashMap<Object, Object> map);
	HashMap<Object, Object> selectDataForResult(HashMap<Object, Object> paramMap);
}
