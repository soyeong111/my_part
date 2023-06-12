package com.study.bookspace.sms;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class SmsVO {
	
	// 개별 수신번호 : 숫자만
	private String to;
	
	// 개별 메시지 제목 : sms에선 사용안됨
	private String subject;
	
	// 개별 메시지 내용
	private String content;

}
