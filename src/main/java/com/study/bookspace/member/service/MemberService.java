package com.study.bookspace.member.service;

import java.util.List;
import java.util.Map;

import com.study.bookspace.member.vo.MemberVO;

public interface MemberService {
	
	// 아이디 중복 체크
	String idDuplicateCheck(String memId);
	
	// 회원가입
	int join(MemberVO memberVO);
	
	// 로그인 회원 정보 조회
	MemberVO getUserInfoForLogin(String memId);
	
	// 아이디 찾기
	List<MemberVO> findIdList(Map<String, Object> mapData);
	
	// 비밀번호 변경 전 아이디 확인
	int checkId(Map<String, Object> mapData);
	
	// 비밀번호 변경
	int changePw(MemberVO memberVO);

}
