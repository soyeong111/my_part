package com.study.bookspace.info.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NoticeVO {
	private String noticeNo;
	private String noticeTitle;
	private String noticeContent;
	private String noticeCreateDate;
	private String memId;

}
