package com.study.bookspace.club.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.bookspace.club.vo.BookClubImageVO;
import com.study.bookspace.club.vo.BookClubMemberVO;
import com.study.bookspace.club.vo.BookClubVO;

@Service("clubService")
public class ClubServiceImpl implements ClubService{
	@Autowired
	private SqlSessionTemplate sqlSession;

	//북클럽 생성 + 이미지 삽입
	@Override
	//@Transactional(rollbackFor = Exception.class)
	public void regClub(BookClubVO bookClubVO) {
		sqlSession.insert("clubMapper.regClub", bookClubVO);
		//sqlSession.insert("clubMapper.insertImg", bookClubVO);
	}
	
	
	

	//북클럽 목록 조회
	@Override
	public List<BookClubVO> getClubList() {
		return sqlSession.selectList("clubMapper.getClubList");
	}

	//북클럽 상세 조회
	@Override
	public BookClubVO getClubDetail(String clubCode) {
		return sqlSession.selectOne("clubMapper.getClubDetail", clubCode);
	}

	@Override
	public void joinClub(BookClubMemberVO bookClubMemberVO) {
		sqlSession.insert("clubMapper.joinClub", bookClubMemberVO);
	}

	@Override
	public boolean isDuplicateClubName(String clubName) {
		int result = sqlSession.selectOne("clubMapper.isDuplicateClubName", clubName);
		return result != 0 ? true : false;
	}

}
