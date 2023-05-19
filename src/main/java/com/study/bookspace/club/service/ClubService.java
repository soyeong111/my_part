package com.study.bookspace.club.service;

import java.util.List;

import com.study.bookspace.club.vo.BookClubImageVO;
import com.study.bookspace.club.vo.BookClubMemberVO;
import com.study.bookspace.club.vo.BookClubVO;
import com.study.bookspace.club.vo.CommunityVO;

public interface ClubService {
	
	//클럽명 중복확인
	boolean isDuplicateClubName(String clubName);
	
	//북클럽 생성
	void regClub(BookClubVO bookClubVO);
	
	//북클럽 목록 조회
	List<BookClubVO> getClubList();
	
	//북클럽 상세 조회
	BookClubVO getClubDetail(String clubCode);
	
	//회원 북클럽 가입
	void joinClub(BookClubMemberVO bookClubMemberVO);
	
	//이미지 삽입
	void insertImg(BookClubImageVO bookClubImageVO);
	
	//다음 등록 클럽 코드 조회
	String getNextClubCode();
	
	//커뮤니티 게시글 목록 조회
	List<CommunityVO> getBoardList();
	
	//커뮤니티 글 작성
	void regBoard(CommunityVO communityVO);
	
	
	
	
	
	
	
	
	
	
	
	
	
}
