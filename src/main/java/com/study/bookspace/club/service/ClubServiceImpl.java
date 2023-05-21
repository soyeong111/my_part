package com.study.bookspace.club.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.bookspace.club.vo.BookClubImageVO;
import com.study.bookspace.club.vo.BookClubMemberVO;
import com.study.bookspace.club.vo.BookClubVO;
import com.study.bookspace.club.vo.CommunityReplyVO;
import com.study.bookspace.club.vo.CommunityVO;

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

	//북클럽 가입
	@Override
	public void joinClub(BookClubMemberVO bookClubMemberVO) {
		sqlSession.insert("clubMapper.joinClub", bookClubMemberVO);
	}

	//클럽명 중복확인
	@Override
	public boolean isDuplicateClubName(String clubName) {
		int result = sqlSession.selectOne("clubMapper.isDuplicateClubName", clubName);
		return result != 0 ? true : false;
	}

	@Override
	public String getNextClubCode() {
		return sqlSession.selectOne("clubMapper.getNextClubCode");
	}

	//북클럽 이미지 삽입
	@Override
	public void insertImg(BookClubImageVO bookClubImageVO) {
		sqlSession.insert("clubMapper.insertImg", bookClubImageVO);
	}

	//게시글 목록 조회
	@Override
	public List<CommunityVO> getBoardList() {
		return sqlSession.selectList("clubMapper.getBoardList");
	}

	//커뮤니티 글 쓰기
	@Override
	public void regBoard(CommunityVO communityVO) {
		sqlSession.insert("clubMapper.regBoard", communityVO);
	}

	//커뮤니티 글 수정
	@Override
	public void updateBoard(CommunityVO communityVO) {
		sqlSession.update("clubMapper.updateBoard", communityVO);
	}

	//게시글 상세 조회
	@Override
	public CommunityVO getBoardDetail(String boardNum) {
		return sqlSession.selectOne("clubMapper.getBoardDetail", boardNum);
	}

	//게시글 삭제
	@Override
	public void deleteBoard(String boardNum) {
		sqlSession.delete("clubMapper.deleteBoard", boardNum);
	}

	//댓글 등록
	@Override
	public void regReply(CommunityReplyVO communityReplyVO) {
		sqlSession.insert("clubMapper.regReply", communityReplyVO);
	}

	//댓글 조회
	@Override
	public List<CommunityReplyVO> getReplyList(String boardNum) {
		return sqlSession.selectList("clubMapper.getReplyList", boardNum);
	}

	

}
