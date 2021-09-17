package com.jdjhaha.vocab.vocab.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.jdjhaha.vocab.vocab.service.VocabGroupService;
import com.jdjhaha.vocab.vocab.service.VocabQuestionResultService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class VocabQuestionResultController {
	
	@Autowired
	private VocabQuestionResultService vocabQuestionResultService;
	
	@GetMapping("/vocab/question/result/list")
	public List<HashMap<Object, Object>> getVocabQuestionResultList( Principal principal) {
		HashMap<Object, Object> paramMap = new HashMap<Object, Object>();
		paramMap.put("username", principal.getName());
		List<HashMap<Object, Object>> resultList =vocabQuestionResultService.selectGroupByData(paramMap);
		
		return resultList;
	}
	
	@GetMapping("/vocab/question/result/list/{groupCode}")
	public List<HashMap<Object, Object>> getVocabQuestionResult( Principal principal, @PathVariable String groupCode) {
		HashMap<Object, Object> paramMap = new HashMap<Object, Object>();
		paramMap.put("username", principal.getName());
		paramMap.put("groupCode", groupCode);
		List<HashMap<Object, Object>> resultList = vocabQuestionResultService.selectData(paramMap);
		
		return resultList;
	}
}
