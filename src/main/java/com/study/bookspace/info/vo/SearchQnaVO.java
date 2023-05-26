package com.study.bookspace.info.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SearchQnaVO {
	private String searchQnaCode;
	private String searchQnaTitle;
	private String searchMemId;
	private String searchQnaCreateDate;
	private String searchIsAdminAnswer;
	private int searchQnaViewCnt;
	private String searchKeyword;

}
