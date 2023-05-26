package com.study.bookspace.sms;

import java.time.LocalDateTime;

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
public class SmsResponseVO {
	
	// 요청 아이디
	private String requestId;
	
	// 요청 시간
	private LocalDateTime requestTime;
	
	// 요청 상태 코드 : 202(성공), 그외(실패)
	private String statusCode;
	
	// 요청 상태명 : success(성공), fail(실패)
	private String statusName;

}
