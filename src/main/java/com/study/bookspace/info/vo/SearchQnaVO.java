package com.study.bookspace.info.vo;

import com.study.bookspace.util.PageVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SearchQnaVO extends PageVO {
	private String searchQnaCode;
	private String searchQnaTitle;
	private String searchMemId;
	private String searchQnaCreateDate;
	private String searchIsAdminAnswer;
	private int searchQnaViewCnt;
	private String searchKeyword;

}
