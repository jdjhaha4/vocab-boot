package com.jdjhaha.vocab.login.mapper;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface LoginUserMapper {
	HashMap<Object, Object> selectData(HashMap<Object, Object> vo);
	HashMap<Object, Object> selectDataByNickname(HashMap<Object, Object> vo);
	int insertData(HashMap<Object,Object> vo);
}
