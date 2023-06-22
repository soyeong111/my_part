package com.study.bookspace.info.service;

import java.util.List;

import com.study.bookspace.info.vo.AnswerVO;
import com.study.bookspace.info.vo.NoticeVO;

public interface AnswerService {

	void insertAnswer(AnswerVO answerVO);
	
	List<AnswerVO> selectAnswer(String qnaCode);
	
	int updateAnswer(AnswerVO answerVO);
	
	int deleteAnswer(String answerCode);
	
	//공지 리스트
	List<NoticeVO> noticeForPublic(NoticeVO noticeVO);
		
	//공지 상세
	NoticeVO noticeDetailForPublic(String noticeCode);
	
	//공지 생성
	void insertNotice(NoticeVO noticeVO);
	
	//공지 삭제
	int deleteNotice(String noticeNo);
	
	//공지 수정
	int updateNotice(NoticeVO noticeVO);
	
}
