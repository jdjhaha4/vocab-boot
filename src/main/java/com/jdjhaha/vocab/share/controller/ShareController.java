package com.jdjhaha.vocab.share.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.jdjhaha.vocab.share.service.ShareService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ShareController {
	
	@Autowired
	private ShareService shareService;
	
	@GetMapping("/share")
	public HashMap<Object, Object> getMyShareGroupList( Principal principal) {
		HashMap<Object, Object> paramMap = new HashMap<Object, Object>();
		paramMap.put("username", principal.getName());
		
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		List<HashMap<Object, Object>> myShareGroupList = shareService.selectMyGroupList(paramMap);
		
		resultMap.put("myShareGroupList", myShareGroupList);
		
		return resultMap;
	}
	
	@GetMapping("/share/others")
	public HashMap<Object, Object> getOthersShareGroupList( Principal principal) {
		HashMap<Object, Object> paramMap = new HashMap<Object, Object>();
		paramMap.put("username", principal.getName());
		
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		List<HashMap<Object, Object>> othersShareGroupList = shareService.selectOthersGroupList(paramMap);
		
		resultMap.put("othersShareGroupList", othersShareGroupList);
		
		return resultMap;
	}
	
	@GetMapping("/share/others/{groupCode}")
	public HashMap<Object, Object> getVocabGroup( Principal principal, @PathVariable String groupCode) {
		HashMap<Object, Object> paramMap = new HashMap<Object, Object>();
		paramMap.put("groupCode", groupCode);
		HashMap<Object, Object> resultMap =shareService.selectOthersGroupData(paramMap);
		
		return resultMap;
	}
	
	@GetMapping("/share/others/vocab/list/{groupCode}")
	public List<HashMap<Object, Object>> getVocabList( Principal principal, @PathVariable String groupCode) {
		HashMap<Object, Object> paramMap = new HashMap<Object, Object>();
		paramMap.put("groupCode", groupCode);
		List<HashMap<Object, Object>> resultList =shareService.selectOthersVocabList(paramMap);
		
		return resultList;
	}
}
