package com.jdjhaha.vocab.vocab.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.jdjhaha.vocab.vocab.service.VocabQuestionResultHistoryService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class VocabQuestionResultHistoryController {
	
	@Autowired
	private VocabQuestionResultHistoryService vocabQuestionResultHistoryService;
	
	@GetMapping("/vocab/question/result/history/list/{vocab_question_result_id}")
	public List<HashMap<Object, Object>> getVocabQuestionResult( Principal principal, @PathVariable String vocab_question_result_id) {
		HashMap<Object, Object> paramMap = new HashMap<Object, Object>();
		paramMap.put("username", principal.getName());
		paramMap.put("vocab_question_result_id", vocab_question_result_id);
		List<HashMap<Object, Object>> resultList = vocabQuestionResultHistoryService.selectData(paramMap);
		
		return resultList;
	}
}
