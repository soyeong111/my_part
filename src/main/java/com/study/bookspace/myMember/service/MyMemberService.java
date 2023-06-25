package com.study.bookspace.myMember.service;

import java.util.List;
import java.util.Map;

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
	
	// 내 프로필 사진 등록, 삭제
	int updateMemImg(MemberVO memberVO);
	
	// 회원 탈퇴 전 사진 경로 받기
	String getMemImgUrlForWithdrawal(String memId);
	
	// 회원 탈퇴
	int withdrawal(String memId);
	
	// 내 프로필 차트 데이터 받기
	List<Map<String, Object>> getMyBorrowCntListForChart(String nowYear);
	
}
