package com.study.bookspace.info.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class QnaVO {
	private String qnaCode;
	private String qnaTitle;
	private String qnaContent;
	private String qnaCreateDate;
	private int qnaViewCnt;
	private String memId;
	

}
