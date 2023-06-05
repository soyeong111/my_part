package com.study.bookspace.member.service;

import java.util.List;
import java.util.Map;

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

	// 로그인 회원 정보 조회
	@Override
	public MemberVO getUserInfoForLogin(String memId) {
		return sqlSession.selectOne("memberMapper.getUserInfoForLogin", memId);
	}

	// 아이디 찾기
	@Override
	public List<MemberVO> findIdList(Map<String, Object> mapData) {
		return sqlSession.selectList("memberMapper.findIdList", mapData);
	}

	// 비밀번호 변경 전 아이디 확인
	@Override
	public int checkId(Map<String, Object> mapData) {
		return sqlSession.selectOne("memberMapper.checkId", mapData);
	}

}
