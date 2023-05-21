package com.study.bookspace.info.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AnswerVO {
	private String answerCode;
	private String answerContent;
	private String answerCreateDate;
	private String memId;
	private String qnaCode;

}
