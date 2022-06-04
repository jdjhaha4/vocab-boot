package com.jdjhaha.vocab.share.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdjhaha.vocab.share.mapper.ShareMapper;
import com.jdjhaha.vocab.share.service.ShareService;

@Service
public class ShareServiceImpl implements ShareService {
	@Autowired
	private ShareMapper shareMapper;
	
	@Override
	public List<HashMap<Object, Object>> selectMyGroupList(HashMap<Object, Object> paramMap) {
		return shareMapper.selectMyGroupList(paramMap);
	}

	@Override
	public List<HashMap<Object, Object>> selectOthersGroupList(HashMap<Object, Object> paramMap) {
		return shareMapper.selectOthersGroupList(paramMap);
	}

	@Override
	public HashMap<Object, Object> selectOthersGroupData(HashMap<Object, Object> paramMap) {
		return shareMapper.selectOthersGroupData(paramMap);
	}

	@Override
	public List<HashMap<Object, Object>> selectOthersVocabList(HashMap<Object, Object> paramMap) {
		return shareMapper.selectOthersVocabList(paramMap);
	}
	
	
}
