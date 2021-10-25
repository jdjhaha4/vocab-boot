package com.jdjhaha.vocab.vocab.vo;

import java.math.BigInteger;
import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
public class VocabQuestionResultHistoryVO {

	private BigInteger id;
	private BigInteger vocab_question_result_id;
	private BigInteger group_code;
	private String question_type;
	private String question_value;
	private BigInteger vocab_id;
	private String vocab;
	private String mean;
	private BigInteger answer_vocab_id;
	private String answer_vocab;
	private String answer_mean;
	private String result_flag;
	private Date regist_datetime;
	private String username;
	
	public VocabQuestionResultHistoryVO() {}

	@Builder
	public VocabQuestionResultHistoryVO(BigInteger vocab_question_result_id, BigInteger group_code, String question_type,
			String question_value, BigInteger vocab_id, String vocab, String mean, BigInteger answer_vocab_id, String answer_vocab,
			String answer_mean, String result_flag, Date regist_datetime, String username) {
		super();
		this.vocab_question_result_id = vocab_question_result_id;
		this.group_code = group_code;
		this.question_type = question_type;
		this.question_value = question_value;
		this.vocab_id = vocab_id;
		this.vocab = vocab;
		this.mean = mean;
		this.answer_vocab_id = answer_vocab_id;
		this.answer_vocab = answer_vocab;
		this.answer_mean = answer_mean;
		this.result_flag = result_flag;
		this.regist_datetime = regist_datetime;
		this.username = username;
	}
	
	
}
