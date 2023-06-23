package com.study.bookspace.myMember.service;

import java.util.List;

import com.study.bookspace.member.vo.MemberVO;

public interface MyMemberService {
	
	// 로그인 후 회원 상태 조회
	String checkMemStatus(String memId);
	
	// 로그인 후 회원 상태, 로그인 날짜 수정
	void updateMemLoginDate(String memId);
	
	// 비밀번호 확인
	String getPwById(String memId);
	
	// 내 정보 정보 조회
	MemberVO getMemberInfo(String memId);
	
	// 내 정보 변경
	int updateMember(MemberVO memberVO);
	
	// 내 프로필 간단 정보
	MemberVO getMyProfile(String memId);
	
}
