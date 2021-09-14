package com.jdjhaha.vocab.vocab.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
public class VocabQuestionResultVO {
	private int id;
	private int group_code; 
	private String group_name; 
	private int vocab_count; 
	private int answer_count;
	private int wrong_answer_count; 
	private String complete_flag; 
	private Date regist_datetime; 
	private Date update_datetime;
	private String username;
	private int study_time_seconds;
	
	public VocabQuestionResultVO() {
		
	}
	
	
	
	public String getRegist_datetime_str() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(regist_datetime);
	}
	
	public String getUpdate_datetime_str() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(update_datetime);
	}


	@Builder
	public VocabQuestionResultVO(int group_code, String group_name, int vocab_count, int answer_count,
			int wrong_answer_count, String complete_flag, String username, int study_time_seconds) {
		super();
		this.group_code = group_code;
		this.group_name = group_name;
		this.vocab_count = vocab_count;
		this.answer_count = answer_count;
		this.wrong_answer_count = wrong_answer_count;
		this.complete_flag = complete_flag;
		this.username = username;
		this.study_time_seconds = study_time_seconds;
	}
}
