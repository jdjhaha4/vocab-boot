package com.jdjhaha.vocab.vocab.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdjhaha.vocab.vocab.service.VocabAttentionMappingService;
import com.jdjhaha.vocab.vocab.service.VocabAttentionService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class VocabAttentionController {
	@Autowired
	private VocabAttentionService vocabAttentionService;
	
	@Autowired
	private VocabAttentionMappingService vocabAttentionMappingService;
	
	@PostMapping("/vocab/attention/list")
	public List<HashMap<Object, Object>> getVocabList(Principal principal) {
		//JSONObject obj = new JSONObject(data);
		HashMap<Object, Object> paramMap = new HashMap<Object, Object>();
		paramMap.put("username", principal.getName());
		
		List<HashMap<Object, Object>> resultList =vocabAttentionService.selectData(paramMap);
		
		return resultList;
	}
	
	@GetMapping("/vocab/attention/detail/{vocab_id}")
	public HashMap<Object, Object> getVocabAttentionDetail( Principal principal, @PathVariable String vocab_id) {
		HashMap<Object, Object> paramMap = new HashMap<Object, Object>();
		paramMap.put("username", principal.getName());
		paramMap.put("vocab_id", vocab_id);
		HashMap<Object, Object> vocabAttentionMap = vocabAttentionService.selectOneData(paramMap);
		List<HashMap<Object, Object>> resultList =vocabAttentionMappingService.selectData(paramMap);
		
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		resultMap.put("resultVocabAttention", vocabAttentionMap);
		resultMap.put("resultVocabAttentionDetailList", resultList);
		
		return resultMap;
	}
}
