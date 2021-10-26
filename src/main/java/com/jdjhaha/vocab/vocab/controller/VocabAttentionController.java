package com.jdjhaha.vocab.vocab.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdjhaha.vocab.vocab.service.VocabAttentionService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class VocabAttentionController {
	@Autowired
	private VocabAttentionService vocabAttentionService;
	
	@PostMapping("/vocab/attention/list")
	public List<HashMap<Object, Object>> getVocabList(Principal principal) {
		//JSONObject obj = new JSONObject(data);
		HashMap<Object, Object> paramMap = new HashMap<Object, Object>();
		paramMap.put("username", principal.getName());
		
		List<HashMap<Object, Object>> resultList =vocabAttentionService.selectData(paramMap);
		
		return resultList;
	}
	
}
