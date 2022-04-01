package com.jdjhaha.vocab.main.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdjhaha.vocab.main.service.MainService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class MainController {
	
	@Autowired
	private MainService mainService;
	
	@GetMapping("/main/data")
	public HashMap<Object, Object> getMainData( Principal principal) {
		log.info("main data");
		HashMap<Object, Object> paramMap = new HashMap<Object, Object>();
		paramMap.put("username", principal.getName());
		
		List<HashMap<Object, Object>> myVocabDataList = mainService.selectMyVocabDataList(paramMap);
		
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		resultMap.put("myVocabDataList", myVocabDataList);
		
		return resultMap;
	}
}
