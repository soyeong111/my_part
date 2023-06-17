package com.study.bookspace.club.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.bookspace.club.vo.BookClubImageVO;
import com.study.bookspace.club.vo.BookClubMemberVO;
import com.study.bookspace.club.vo.BookClubVO;
import com.study.bookspace.club.vo.CommunityImageVO;
import com.study.bookspace.club.vo.CommunityReplyVO;
import com.study.bookspace.club.vo.CommunityVO;
import com.study.bookspace.util.PageVO;

@Service("clubService")
public class ClubServiceImpl implements ClubService{
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	//클럽장 클럽코드 조회
	@Override
	public String getClubCode(String memId) {
		return sqlSession.selectOne("clubMapper.getClubCode", memId);
	}
	
	//북클럽 생성 + 이미지 삽입
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void regClub(BookClubVO bookClubVO, BookClubImageVO bookClubImageVO, BookClubMemberVO bookClubMemberVO) {
		sqlSession.insert("clubMapper.regClub", bookClubVO);
		sqlSession.insert("clubMapper.insertImg", bookClubImageVO);
		sqlSession.insert("clubMapper.joinClub", bookClubMemberVO);
		sqlSession.update("clubMapper.updateClubMemberStatus", bookClubMemberVO);
		sqlSession.update("clubMapper.updateClubMemberRole", bookClubMemberVO);
	}
	
	//클럽 회원수 조회
	@Override
	public int countMemCnt(String clubCode) {
		return sqlSession.selectOne("clubMapper.countMemCnt", clubCode);
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

	//클럽 가입 이력
	@Override
	public boolean alreadyApply(BookClubMemberVO bookClubMemberVO) {
		int result = sqlSession.selectOne("clubMapper.alreadyApply", bookClubMemberVO);
		return result != 0 ? true : false;
	}
	
	//북클럽 가입
	@Override
	public void joinClub(BookClubMemberVO bookClubMemberVO) {
		sqlSession.insert("clubMapper.joinClub", bookClubMemberVO);
	}
	
	//클럽 가지고 있는지 확인
	@Override
	public boolean hasClub(String memId) {
		int result = sqlSession.selectOne("clubMapper.hasClub", memId);
		return result != 0 ? true : false;
	}
	
	//클럽명 중복확인
	@Override
	public boolean isDuplicateClubName(String clubName) {
		int result = sqlSession.selectOne("clubMapper.isDuplicateClubName", clubName);
		return result != 0 ? true : false;
	}
	
	//클럽코드
	@Override
	public String getNextClubCode() {
		return sqlSession.selectOne("clubMapper.getNextClubCode");
	}

	//게시글 목록 조회
	@Override
	public List<CommunityVO> getBoardList(CommunityVO communityVO) {
		return sqlSession.selectList("clubMapper.getBoardList", communityVO);
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

	//게시글 댓글 수정
	@Override
	public void updateReply(CommunityReplyVO communityReplyVO) {
		sqlSession.update("clubMapper.updateReply", communityReplyVO);
	}
	
	//댓글 삭제
	@Override
	public void deleteReply(String replyNum) {
		sqlSession.delete("clubMapper.deleteReply", replyNum);
	}
	
	//클럽 회원 승인
	@Override
	public void acceptMember(String acceptCode) {
		sqlSession.update("clubMapper.acceptMember", acceptCode);
	}

	//게시글 수 조회
	@Override
	public int getBoardCnt(String clubCode) {
		return sqlSession.selectOne("clubMapper.getBoardCnt", clubCode);
	}

	//게시글 조회수 증가
	@Override
	public int updateReadCnt(String boardNum) {
		return sqlSession.update("clubMapper.updateReadCnt", boardNum);
	}

	//클럽 삭제
	@Override
	public void deleteClub(String clubCode) {
		sqlSession.delete("clubMapper.deleteClub", clubCode);
	}

	//클럽 수정
	@Override
	public void updateClub(BookClubVO bookClubVO) {
		sqlSession.update("clubMapper.updateClub", bookClubVO);
	}

	//클럽 회원 목록 조회
	@Override
	public List<BookClubMemberVO> getClubMemberList(String clubCode) {
		return sqlSession.selectList("clubMapper.getClubMemberList", clubCode);
	}

	//가입 신청 회원 목록 조회
	@Override
	public List<BookClubMemberVO> getApplyMemberList(String clubCode) {
		return sqlSession.selectList("clubMapper.getApplyMemberList", clubCode);
	}

	//회원 거절/강퇴
	@Override
	public void refuseMember(String acceptCode) {
		sqlSession.update("clubMapper.refuseMember", acceptCode);
	}

	//클럽 멤버인지 확인(커뮤니티)
	@Override
	public boolean isClubMember(BookClubMemberVO bookClubMemberVO) {
		int result = sqlSession.selectOne("clubMapper.isClubMember", bookClubMemberVO);
		return result != 0 ? true : false;
	}

	//커뮤니티 공지사항 작성
	@Override
	public void regNotice(CommunityVO communityVO) {
		sqlSession.insert("clubMapper.regNotice", communityVO);
	}

	//클럽 이미지 이름 조회
	@Override
	public String getClubImageName(String clubCode) {
		return sqlSession.selectOne("clubMapper.getClubImageName", clubCode);
	}

	//북클럽장 아이디 조회
	@Override
	public String getClubBossId(String clubCode) {
		return sqlSession.selectOne("clubMapper.getClubBossId", clubCode);
	}

	//나의 클럽 신청 대기 상태 조회
	@Override
	public List<BookClubMemberVO> getMyClubDetail(String memId) {
		return sqlSession.selectList("clubMapper.getMyClubDetail", memId);
	}

	//클럽가입 취소하기
	@Override
	public void cancelApply(String acceptCode) {
		sqlSession.delete("clubMapper.cancelApply", acceptCode);
	}

	
	//클럽코드로 클럽장 아이디 구하기
	@Override
	public String getMemIdByClubCode(String clubCode) {
		return sqlSession.selectOne("clubMapper.getMemIdByClubCode", clubCode);
	}
	
	//커뮤니티 이미지 삽입
	@Override
	public void insertCommunityImg(CommunityImageVO communityImageVO) {
		sqlSession.insert("clubMapper.insertCommunityImg", communityImageVO);
	}

	@Override
	public String getNextBoardNum() {
		return sqlSession.selectOne("clubMapper.getNextBoardNum");
	}

	//게시글 이미지 이름
	@Override
	public String getCommunityImageName(String boardNum) {
		return sqlSession.selectOne("clubMapper.getCommunityImageName", boardNum);
	}

	//공지사항 조회
	@Override
	public List<CommunityVO> getNoticeList(String clubCode) {
		return sqlSession.selectList("clubMapper.getNoticeList", clubCode);
	}

	//클럽 회원 리스트 
	@Override
	public List<BookClubMemberVO> getMemListByClub(String clubCode) {
		return sqlSession.selectList("clubMapper.getMemListByClub", clubCode);
	}

	
	


	
	

	





	
	


	


	


	


}
