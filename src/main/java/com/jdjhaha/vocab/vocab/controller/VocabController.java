package com.jdjhaha.vocab.vocab.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jdjhaha.vocab.vocab.service.VocabService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class VocabController {
	
	@Autowired
	private VocabService vocabService;
	
	@GetMapping("/vocab/list")
	public List<HashMap<Object, Object>> authCheck(Principal principal) {
		List<HashMap<Object, Object>> resultList =vocabService.selectData();
		
		return resultList;
	}
	
	@PutMapping("/vocab/insert")
	public int saveVocaData(@RequestBody String data, Principal principal){
		JSONObject obj = new JSONObject(data);
		
		HashMap<Object, Object> paramMap = new HashMap<>();
		paramMap.put("voca_json", obj.getString("vocaJson"));
		paramMap.put("username", principal.getName());
		int resultCnt = vocabService.insertData(paramMap);
		
		return resultCnt;
	}
}
