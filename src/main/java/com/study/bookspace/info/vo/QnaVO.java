package com.study.bookspace.info.vo;

import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class QnaVO {
	private String qnaCode;
	private int qnaNum;
	private String qnaTitle;
	private String qnaContent;
	private String qnaCreateDate;
	private int qnaViewCnt;
	private String memId;
	private int rowNum;
	private int rowNumber;
	private String isAdminAnswer;
	 
}
