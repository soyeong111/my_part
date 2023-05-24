package com.study.bookspace.util;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MailVO {
	
	// 제목
	private String title;
	
	// 내용
	private String content;
	
	// 이메일 리스트
	private List<String> recipientList;

}
