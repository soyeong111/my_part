package com.study.bookspace.sms;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SmsRequestVO {
	
	// SMS Type : SMS, LMS, MMS
	private String type;
	
	// 메시지 Type : COMM(일반메시지), AD(광고메시지) (default=COMM)
	private String contentType;
	
	// 국가 번호 : default=82(대한민국)
	private String countryCode;
	
	// 발신번호 : 숫자만
	private String from;
	
	// 기본 메시지 내용 : 안 쓰면 "" 라도 있어야 함
	private String content;
	
	// 메시지 정보
	private List<SmsVO> messages;

}
