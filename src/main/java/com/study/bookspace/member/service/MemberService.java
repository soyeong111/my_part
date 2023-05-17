package com.study.bookspace.member.service;

import com.study.bookspace.member.vo.MemberVO;

public interface MemberService {
	
	// 아이디 중복 체크
	String idDuplicateCheck(String memId);
	
	// 회원가입
	int join(MemberVO memberVO);

}
