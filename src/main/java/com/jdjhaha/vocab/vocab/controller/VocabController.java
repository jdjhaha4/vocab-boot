package com.jdjhaha.vocab.vocab.controller;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jdjhaha.vocab.vocab.service.VocabDictionaryService;
import com.jdjhaha.vocab.vocab.service.VocabGroupMappingService;
import com.jdjhaha.vocab.vocab.service.VocabService;
import com.jdjhaha.vocab.vocab.vo.VocabVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class VocabController {
	
	@Autowired
	private VocabService vocabService;
	
	@Autowired
	private VocabDictionaryService vocabDictionaryService;
	
	@Autowired
	private VocabGroupMappingService vocabGroupMappingService;
	
	@PostMapping("/vocab/list")
	public List<HashMap<Object, Object>> getVocabList(@RequestBody String data, Principal principal) {
		JSONObject obj = new JSONObject(data);
		HashMap<Object, Object> paramMap = new HashMap<Object, Object>();
		paramMap.put("username", principal.getName());
		String groupCode = obj.getString("groupCode");
		if(!"".equals(groupCode)) {
			paramMap.put("groupCode", groupCode);
		}
		List<Object> ignoreVocabIdList = obj.getJSONArray("ignoreVocabIdList").toList();
		if(ignoreVocabIdList != null && ignoreVocabIdList.size()>0) {
			paramMap.put("ignoreVocabIdList", ignoreVocabIdList);
		}
		List<HashMap<Object, Object>> resultList =vocabService.selectData(paramMap);
		
		return resultList;
	}
	
	@PostMapping("/vocab/insert")
	public int saveVocaData(@RequestBody String data, Principal principal){
		JSONObject obj = new JSONObject(data);
		String selectedGroupCode = obj.getString("selectedGroupCode");
		obj.remove("selectedGroupCode");
		
		VocabVO vocabVO = new VocabVO();
		vocabVO.setVoca_json(obj.toString());
		vocabVO.setUsername(principal.getName());
		
		String vocab = obj.getString("vocab");
		if(vocab != null && !"".equals(vocab.trim())) {
			HashMap<String, String> dicMap = new HashMap<>();
			dicMap.put("vocab", obj.getString("vocab"));
			dicMap.put("username", principal.getName());
			
			vocabDictionaryService.requestDataMacMillan(dicMap);
			vocabVO.setVocab_dic(vocab);
		}
		
		int resultCnt = vocabService.insertData(vocabVO);
		
		if(selectedGroupCode != null && !("all".equals(selectedGroupCode) || "none".equals(selectedGroupCode))) {
			HashMap<Object, Object> paramMap = new HashMap<>();
			paramMap.put("group_code", new BigDecimal(selectedGroupCode));
			paramMap.put("vocab_id", vocabVO.getId());
			paramMap.put("username", principal.getName());
			vocabGroupMappingService.insertData(paramMap);
		}
		
		return resultCnt;
	}
	
	@PostMapping("/vocab/update")
	public VocabVO updateVocaData(@RequestBody String data, Principal principal){
		JSONObject obj = new JSONObject(data);
		int vocabId = obj.getInt("id");
		obj.remove("id");
		
		VocabVO vocabVO = new VocabVO();
		vocabVO.setId(vocabId);
		vocabVO.setVoca_json(obj.toString());
		vocabVO.setUsername(principal.getName());
		
		int resultCnt = vocabService.updateData(vocabVO);
		if(resultCnt >0) {
			return vocabVO;
		}else {
			return null;
		}
	}
	
	@PostMapping("/vocab/delete")
	public BigInteger wordDelete(@RequestBody String data, Principal principal){
		JSONObject obj = new JSONObject(data);
		
		HashMap<Object, Object> paramMap = new HashMap<>();
		paramMap.put("username", principal.getName());
		paramMap.put("id", obj.getBigInteger("id"));
		int resultCnt = vocabService.deleteData(paramMap);
		if(resultCnt != 1) {
			return new BigInteger("0");
		}
		
		return obj.getBigInteger("id");
	}
}
