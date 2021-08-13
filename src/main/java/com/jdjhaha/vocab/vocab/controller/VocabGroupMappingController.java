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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jdjhaha.vocab.vocab.service.VocabGroupMappingService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class VocabGroupMappingController {
	
	@Autowired
	private VocabGroupMappingService vocabGroupMappingService;
	
	@GetMapping("/vocab/group/mapping/list")
	public List<HashMap<Object, Object>> getVocabList(@RequestParam(required=false) String groupCode, Principal principal) {
		HashMap<Object, Object> paramMap = new HashMap<Object, Object>();
		paramMap.put("username", principal.getName());
		paramMap.put("group_code", groupCode);
		List<HashMap<Object, Object>> resultList =vocabGroupMappingService.selectData(paramMap);
		
		return resultList;
	}
	
	@PostMapping("/vocab/group/mapping/insert")
	public int saveVocaData(@RequestBody String data, Principal principal){
		log.info(data);
		JSONObject obj = new JSONObject(data);
		
		HashMap<Object, Object> paramMap = new HashMap<>();
		paramMap.put("group_code", obj.getBigInteger("groupCode"));
		paramMap.put("vocab_id", obj.getBigInteger("vocabId"));
		paramMap.put("username", principal.getName());
		int resultCnt = vocabGroupMappingService.insertData(paramMap);
		
		return resultCnt;
	}
	
	@PostMapping("/vocab/group/mapping/delete")
	public BigInteger wordDelete(@RequestBody String data, Principal principal){
		JSONObject obj = new JSONObject(data);
		
		HashMap<Object, Object> paramMap = new HashMap<>();
		paramMap.put("group_code", obj.getBigInteger("groupCode"));
		paramMap.put("vocab_id", obj.getBigInteger("vocabId"));
		paramMap.put("username", principal.getName());
		int resultCnt = vocabGroupMappingService.deleteData(paramMap);
		if(resultCnt != 1) {
			return new BigInteger("0");
		}
		
		return obj.getBigInteger("groupCode");
	}
}
