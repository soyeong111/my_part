package com.study.bookspace.club.service;

import java.util.List;

import com.study.bookspace.club.vo.BookClubImageVO;
import com.study.bookspace.club.vo.BookClubMemberVO;
import com.study.bookspace.club.vo.BookClubVO;
import com.study.bookspace.club.vo.CommunityImageVO;
import com.study.bookspace.club.vo.CommunityReplyVO;
import com.study.bookspace.club.vo.CommunityVO;
import com.study.bookspace.util.PageVO;

public interface ClubService {
	
	//클럽장 클럽코드 조회
	String getClubCode(String memId);
	
	//클럽 가지고 있는지 확인
	boolean hasClub(String memId);
	
	//클럽명 중복확인
	boolean isDuplicateClubName(String clubName);
	
	//북클럽 생성 @@ 
	void regClub(BookClubVO bookClubVO, BookClubImageVO bookClubImageVO, BookClubMemberVO bookClubMemberVO);
	
	//북클럽 목록 조회
	List<BookClubVO> getClubList();
	
	//북클럽 상세 조회
	BookClubVO getClubDetail(String clubCode);
	
	//클럽 회원수 조회
	int countMemCnt(String clubCode);
	
	//클럽 가입 이력
	boolean alreadyApply(BookClubMemberVO bookClubMemberVO);
	
	//회원 북클럽 가입
	void joinClub(BookClubMemberVO bookClubMemberVO);
	
	//북클럽장 아이디 조회
	String getClubBossId(String clubCode);
	
	//나의 클럽 신청 대기 상태 조회
	List<BookClubMemberVO> getMyClubDetail(String memId);
	
	//이미지 삽입
	void insertImg(BookClubImageVO bookClubImageVO);
	
	//다음 등록 클럽 코드 조회
	String getNextClubCode();
	
	//커뮤니티 게시글 목록 조회
	List<CommunityVO> getBoardList(CommunityVO communityVO);
	
	//커뮤니티 글 작성
	void regBoard(CommunityVO communityVO);
	
	//커뮤니티 공지사항 작성
	void regNotice(CommunityVO communityVO);
	
	//커뮤니티 글 수정
	void updateBoard(CommunityVO communityVO);
	
	//커뮤니티 글 상세 조회
	CommunityVO getBoardDetail(String boardNum);
	
	//커뮤니티 글 삭제
	void deleteBoard(String boardNum);
	
	//게시글 댓글 등록
	void regReply(CommunityReplyVO communityReplyVO);
	
	//댓글 조회
	List<CommunityReplyVO> getReplyList(String boardNum);
	
	//게시글 댓글 수정
	void updateReply(CommunityReplyVO communityReplyVO);
	
	//게시글 댓글 삭제
	void deleteReply(String replyNum);
	
	//클럽 멤버 승인
	String acceptMember(String acceptCode);
	
	//클럽 회원 거절
	String refuseApply(String acceptCode);
	
	//클럽 신청 취소
	void cancelApply(String acceptCode);

	//북클럽 강퇴
	String kickOutMember(String acceptCode);
	
	//클럽 멤버인지 확인
	boolean isClubMember(BookClubMemberVO bookClubMemberVO);
	
	//전체 게시글 수 조회
	int getBoardCnt(String clubCode);
	
	//조회수 증가
	int updateReadCnt(String boardNum);
	
	//클럽 이미지 이름 조회
	String getClubImageName(String clubCode);
	
	//클럽 삭제
	void deleteClub(String clubCode);
	
	//클럽 수정
	void updateClub(BookClubVO bookClubVO);
	
	//클럽 회원 목록 조회
	List<BookClubMemberVO> getClubMemberList(String clubCode);
	
	//클럽 가입 신청 회원 목록 조회(승인 전)
	List<BookClubMemberVO> getApplyMemberList(String clubCode);
	
	//클럽코드로 클럽장 아이디 구하기
	String getMemIdByClubCode(String clubCode);
	
	//커뮤니티 이미지 삽입
	void insertCommunityImg(CommunityImageVO communityImageVO);
	
	//다음 게시글 번호 조회
	String getNextBoardNum();
	
	//게시글 이미지 이름
	String getCommunityImageName(String boardNum);
	
	//공지사항 조회
	List<CommunityVO> getNoticeList(String clubCode);
	
	//클럽 회원 리스트
	List<BookClubMemberVO> getMemListByClub(String clubCode);
	
	//북클럽 이미지 삭제
	void deleteClubImg(String clubImgCode);
	
	//커뮤니티 이미지 삭제
	void deleteComImg(String comImgCode);

	
}
