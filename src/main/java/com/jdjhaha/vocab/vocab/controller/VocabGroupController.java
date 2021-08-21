package com.jdjhaha.vocab.vocab.controller;

import java.math.BigInteger;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jdjhaha.vocab.vocab.service.VocabGroupMappingService;
import com.jdjhaha.vocab.vocab.service.VocabGroupService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class VocabGroupController {
	
	@Autowired
	private VocabGroupService vocabGroupService;
	
	@GetMapping("/vocab/group/list")
	public List<HashMap<Object, Object>> getVocabList( Principal principal) {
		HashMap<Object, Object> paramMap = new HashMap<Object, Object>();
		paramMap.put("username", principal.getName());
		List<HashMap<Object, Object>> resultList =vocabGroupService.selectData(paramMap);
		
		return resultList;
	}
	
	@PostMapping("/vocab/group/insert")
	public int saveVocaData(@RequestBody String data, Principal principal){
		log.info(data);
		JSONObject obj = new JSONObject(data);
		
		HashMap<Object, Object> paramMap = new HashMap<>();
		paramMap.put("group_name", obj.getString("group_name"));
		paramMap.put("username", principal.getName());
		int resultCnt = vocabGroupService.insertData(paramMap);
		
		return resultCnt;
	}
	
	@PostMapping("/vocab/group/delete")
	public BigInteger wordDelete(@RequestBody String data, Principal principal){
		JSONObject obj = new JSONObject(data);
		
		HashMap<Object, Object> paramMap = new HashMap<>();
		paramMap.put("username", principal.getName());
		paramMap.put("group_code", obj.getBigInteger("group_code"));
		int resultCnt = vocabGroupService.deleteData(paramMap);
		if(resultCnt != 1) {
			return new BigInteger("0");
		}
		
		return obj.getBigInteger("group_code");
	}
}
