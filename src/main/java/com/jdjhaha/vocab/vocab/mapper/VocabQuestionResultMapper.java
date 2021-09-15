package com.jdjhaha.vocab.vocab.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.jdjhaha.vocab.vocab.vo.VocabQuestionResultVO;

@Repository
@Mapper
public interface VocabQuestionResultMapper {
	List<HashMap<Object, Object>> selectData(HashMap<Object, Object> paramMap);
	
	int insertData2(VocabQuestionResultVO vocabQuestionResultVO);
	int insertData(HashMap<Object, Object> paramMap);
	int updateData(HashMap<Object, Object> paramMap);
	int deleteData(HashMap<Object, Object> map);
}
