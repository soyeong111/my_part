package com.study.bookspace.club.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommunityVO {
	private String boardNum;
	private String boardTitle;
	private String boardContent;
	private String boardWriter;
	private String regDate;
	private int readCnt;
}
