package com.study.bookspace.member.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberVO {
	
	private String memId;
	private String memPw;
	private String memName;
	private String memStatus; // '휴면', '활동', '탈퇴'
	private String memRole; // 'USER', 'ADMIN'
	private String memEmail;
	private String memTell;
	private String memBirthday;
	private String memGender; // 'M', 'F'
	private String memAddr;
	private String memAddrDetail;
	private String memJoinDate;
	private String borrowRestrictDuedate; // 대여 제한 마지막 날짜
	private String memImgUrl;
	
	private String[] memEmailArr;
	private String[] memTellArr;
	
	public void setMemEmailArr(String[] memEmailArr) {
		this.memEmailArr = memEmailArr;
		this.memEmail = memEmailArr[0] + "@" + memEmailArr[1];
	}
	
	public void setMemTellArr(String[] memTellArr) {
		this.memTellArr = memTellArr;
		this.memTell = memTellArr[0] + "-" + memTellArr[1] + "-" + memTellArr[2];
	}
	
}
