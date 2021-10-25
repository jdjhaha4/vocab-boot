package com.jdjhaha.vocab.vocab.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jdjhaha.vocab.vocab.service.VocabAttentionService;
import com.jdjhaha.vocab.vocab.service.VocabQuestionResultHistoryService;
import com.jdjhaha.vocab.vocab.service.VocabQuestionResultService;
import com.jdjhaha.vocab.vocab.vo.VocabQuestionResultHistoryVO;
import com.jdjhaha.vocab.vocab.vo.VocabQuestionResultVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class VocabStudyMultipleController {
	
	@Autowired
	private VocabQuestionResultService vocabQuestionResultService;
	
	@Autowired
	private VocabQuestionResultHistoryService vocabQuestionResultHistoryService;
	
	@Autowired
	private VocabAttentionService vocabAttentionService;
	
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
	@PostMapping("/vocab/question/result/insert")
	public HashMap<Object, Object> vocabQuestionResultInsert(@RequestBody String data, Principal principal){
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();

		JSONObject obj = new JSONObject(data);
		
		HashMap<Object, Object> paramMap = new HashMap<Object, Object>();
		paramMap.put("username", principal.getName());
		paramMap.put("groupCode", obj.getInt("group_code"));
		List<HashMap<Object, Object>> resultList = vocabQuestionResultService.selectData(paramMap);
		int round = 1;
		if(resultList != null) {
			round = resultList.size()+1;
		}
		
		VocabQuestionResultVO vqrVO = VocabQuestionResultVO.builder()
															.group_code(obj.getInt("group_code"))
															.group_name(obj.getString("group_name"))
															.vocab_count(obj.getInt("vocab_count"))
															.answer_count(obj.getInt("answer_count"))
															.wrong_answer_count(obj.getInt("wrong_answer_count"))
															.complete_flag(obj.getString("complete_flag"))
															.username(principal.getName())
															.study_time_seconds(obj.getInt("study_time_seconds"))
															.round(round)
															.build();
		vocabQuestionResultService.insertData(vqrVO);
		resultMap.put("vocab_question_result_id", vqrVO.getId());
		return resultMap;
	}
	
	@PostMapping("/vocab/question/result/update")
	public HashMap<Object, Object> vocabQuestionResultUpdate(@RequestBody String data, Principal principal){
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		log.info(data);
		JSONObject obj = new JSONObject(data);
		
		HashMap<Object, Object> paramMap = new HashMap<>();
		paramMap.put("id", obj.getBigInteger("id"));
		paramMap.put("answer_count", obj.getInt("answer_count"));
		paramMap.put("wrong_answer_count", obj.getInt("wrong_answer_count"));
		paramMap.put("complete_flag", obj.getString("complete_flag"));
		paramMap.put("study_time_seconds", obj.getInt("study_time_seconds"));
		paramMap.put("username", principal.getName());
		
		vocabQuestionResultService.updateData(paramMap);
		
		resultMap.put("result_code", "success");
		return resultMap;
	}
	
	@PostMapping("/vocab/study/multiple/post")
	public int saveVocaData(@RequestBody String data, Principal principal){
		log.info(data);
		JSONObject obj = new JSONObject(data);
		
		VocabQuestionResultHistoryVO vqrhVO = VocabQuestionResultHistoryVO.builder()
																		.vocab_question_result_id(obj.getBigInteger("vocab_question_result_id"))
																		.group_code(obj.getBigInteger("group_code"))
																		.question_type(obj.getString("question_type"))
																		.question_value(obj.getString("question_value"))
																		.vocab_id(obj.getBigInteger("vocab_id"))
																		.vocab(obj.getString("vocab"))
																		.mean(obj.getString("mean"))
																		.answer_vocab_id(obj.getBigInteger("answer_vocab_id"))
																		.answer_vocab(obj.getString("answer_vocab"))
																		.answer_mean(obj.getString("answer_mean"))
																		.result_flag(obj.getString("result_flag"))
																		.username(principal.getName())
																		.build();
		vocabQuestionResultHistoryService.insertData2(vqrhVO);
		
		if(obj.getString("result_flag") != null && !"T".equals(obj.getString("result_flag"))) {
			HashMap<Object, Object> paramMap = new HashMap<Object, Object>();
			
			paramMap.put("vocab_id", vqrhVO.getVocab_id());
			paramMap.put("vocab", vqrhVO.getVocab());
			paramMap.put("mean", vqrhVO.getMean());
			paramMap.put("vocab_question_result_id", vqrhVO.getVocab_question_result_id());
			paramMap.put("vocab_question_result_history_id", vqrhVO.getId());
			paramMap.put("username", principal.getName());
			
			vocabAttentionService.insertData(paramMap );
		}
		
		return 0;
	}
	
}
