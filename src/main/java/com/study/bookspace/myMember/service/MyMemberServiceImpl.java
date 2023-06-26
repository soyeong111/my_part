package com.study.bookspace.myMember.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.bookspace.member.vo.MemberVO;

@Service("myMemberService")
public class MyMemberServiceImpl implements MyMemberService {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	// 로그인 후 회원 상태 조회
	@Override
	public String checkMemStatus(String memId) {
		return sqlSession.selectOne("memberMapper.checkMemStatus", memId);
	}

	// 로그인 후 회원 상태, 로그인 날짜 수정
	@Override
	public void updateMemLoginDate(String memId) {
		sqlSession.update("memberMapper.updateMemLoginDate", memId);
	}

	// 비밀번호 확인
	@Override
	public String getPwById(String memId) {
		return sqlSession.selectOne("memberMapper.getPwById", memId);
	}

	// 내 정보 정보 조회
	@Override
	public MemberVO getMemberInfo(String memId) {
		return sqlSession.selectOne("memberMapper.getMemberInfo", memId);
	}

	// 내 정보 변경
	@Override
	public int updateMember(MemberVO memberVO) {
		return sqlSession.update("memberMapper.updateMember", memberVO);
	}

	// 내 프로필 간단 정보
	@Override
	public MemberVO getMyProfile(String memId) {
		return sqlSession.selectOne("memberMapper.getMyProfile", memId);
	}
	
}
