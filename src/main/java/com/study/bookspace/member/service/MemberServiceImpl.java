package com.study.bookspace.member.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.bookspace.member.vo.MemberVO;

@Service("memberService")
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	// 아이디 중복 체크
	@Override
	public String idDuplicateCheck(String memId) {
		return sqlSession.selectOne("memberMapper.idDuplicateCheck", memId);
	}

	// 회원가입
	@Override
	public int join(MemberVO memberVO) {
		return sqlSession.insert("memberMapper.join", memberVO);
	}

}
