package com.jdjhaha.vocab.vocab.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdjhaha.vocab.vocab.mapper.VocabGroupMapper;
import com.jdjhaha.vocab.vocab.mapper.VocabGroupMappingMapper;
import com.jdjhaha.vocab.vocab.service.VocabGroupService;

@Service
public class VocabGroupServiceImpl implements VocabGroupService {
	
	@Autowired
	private VocabGroupMapper vocabGroupMapper;
	
	@Autowired
	private VocabGroupMappingMapper vocabGroupMappingMapper;
	
	@Override
	public List<HashMap<Object, Object>> selectData(HashMap<Object, Object> paramMap) {
		return vocabGroupMapper.selectData(paramMap);
	}

	@Override
	public HashMap<Object, Object> selectOneData(HashMap<Object, Object> paramMap) {
		List<HashMap<Object, Object>> resultList = vocabGroupMapper.selectData(paramMap);
		if(resultList != null && resultList.size()==1) {
			return resultList.get(0);
		}else {
			return null;
		}
	}

	@Override
	public List<HashMap<Object, Object>> selectOthersReleaseData(HashMap<Object, Object> paramMap) {
		return vocabGroupMapper.selectOthersReleaseData(paramMap);
	}

	@Override
	public int insertData(HashMap<Object, Object> paramMap) {
		return vocabGroupMapper.insertData(paramMap);
	}

	@Override
	public int updateData(HashMap<Object, Object> paramMap) {
		return vocabGroupMapper.updateData(paramMap);
	}

	@Override
	public int deleteData(HashMap<Object, Object> paramMap) {
		vocabGroupMappingMapper.deleteDataByGroupCode(paramMap);
		return vocabGroupMapper.deleteData(paramMap);
	}

	@Override
	public HashMap<Object, Object> selectDataForResult(HashMap<Object, Object> paramMap) {
		return vocabGroupMapper.selectDataForResult(paramMap);
	}

}
