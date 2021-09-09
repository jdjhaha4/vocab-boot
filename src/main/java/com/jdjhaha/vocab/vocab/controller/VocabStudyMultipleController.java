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
public class VocabStudyMultipleController {
	
//	@Autowired
//	private VocabGroupMappingService vocabGroupMappingService;
	
//	@GetMapping("/vocab/group/mapping/list")
//	public List<HashMap<Object, Object>> getVocabList(@RequestParam(required=false) String groupCode, Principal principal) {
//		HashMap<Object, Object> paramMap = new HashMap<Object, Object>();
//		paramMap.put("username", principal.getName());
//		paramMap.put("group_code", groupCode);
//		List<HashMap<Object, Object>> resultList =vocabGroupMappingService.selectData(paramMap);
//		
//		return resultList;
//	}
	///vocab/study/multiple/post', { vocab_id, result_flag });
	@PostMapping("/vocab/study/multiple/post")
	public int saveVocaData(@RequestBody String data, Principal principal){
		log.info(data);
		JSONObject obj = new JSONObject(data);
		
		HashMap<Object, Object> paramMap = new HashMap<>();
		paramMap.put("vocab_id", obj.getBigInteger("vocab_id"));
		paramMap.put("vocab", obj.getString("vocab"));
		paramMap.put("mean", obj.getString("mean"));
		paramMap.put("answer_vocab_id", obj.getBigInteger("answer_vocab_id"));
		paramMap.put("answer_vocab", obj.getString("answer_vocab"));
		paramMap.put("answer_mean", obj.getString("answer_mean"));
		paramMap.put("result_flag", obj.getString("result_flag"));
		paramMap.put("username", principal.getName());
		
		return 0;
	}
	
}
