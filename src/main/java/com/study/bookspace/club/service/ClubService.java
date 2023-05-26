package com.study.bookspace.club.service;

import java.util.List;

import com.study.bookspace.club.vo.BookClubImageVO;
import com.study.bookspace.club.vo.BookClubMemberVO;
import com.study.bookspace.club.vo.BookClubVO;
import com.study.bookspace.club.vo.CommunityReplyVO;
import com.study.bookspace.club.vo.CommunityVO;
import com.study.bookspace.util.PageVO;

public interface ClubService {
	
	//클럽 가지고 있는지 확인
	boolean hasClub(String memId);
	
	//클럽명 중복확인
	boolean isDuplicateClubName(String clubName);
	
	//북클럽 생성 @@ 
	void regClub(BookClubVO bookClubVO);
	
	//북클럽 목록 조회
	List<BookClubVO> getClubList();
	
	//북클럽 상세 조회
	BookClubVO getClubDetail(String clubCode);
	
	//클럽 가입 이력
	boolean alreadyApply(BookClubMemberVO bookClubMemberVO);
	
	//회원 북클럽 가입
	void joinClub(BookClubMemberVO bookClubMemberVO);
	
	//이미지 삽입
	void insertImg(BookClubImageVO bookClubImageVO);
	
	//다음 등록 클럽 코드 조회
	String getNextClubCode();
	
	//커뮤니티 게시글 목록 조회
	List<CommunityVO> getBoardList(CommunityVO communityVO);
	
	//커뮤니티 글 작성
	void regBoard(CommunityVO communityVO);
	
	//커뮤니티 글 수정
	void updateBoard(CommunityVO communityVO);
	
	//커뮤니티 글 상세 조회
	CommunityVO getBoardDetail(String boardNum);
	
	//커뮤니티 글 삭제
	void deleteBoard(String boardNum);
	
	//게시글 댓글 등록
	void regReply(CommunityReplyVO communityReplyVO);
	
	//댓글 조회
	List<CommunityReplyVO> getReplyList(CommunityReplyVO communityReplyVO);
	
	//게시글 댓글 수정
	void updateReply(CommunityReplyVO communityReplyVO);
	
	//게시글 댓글 삭제
	void deleteReply(String replyNum);
	
	//클럽 멤버 승인
	void acceptMember(String acceptCode);
	
	//클럽 회원 거절
	void refuseMember(String acceptCode);

	//클럽 멤버인지 확인
	boolean isClubMember(BookClubMemberVO bookClubMemberVO);
	
	//전체 게시글 수 조회
	int getBoardCnt(String clubCode);
	
	//조회수 증가
	int updateReadCnt(CommunityVO communityVO);
	
	
	//클럽 삭제
	void deleteClub(String clubCode);
	
	//클럽 수정
	void updateClub(BookClubVO bookClubVO);
	
	//클럽 회원 목록 조회
	List<BookClubMemberVO> getClubMemberList(String clubCode);
	
	//클럽 가입 신청 회원 목록 조회(승인 전)
	List<BookClubMemberVO> getApplyMemberList(String clubCode);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
