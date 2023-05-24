package com.study.bookspace.info.service;

import java.util.List;

import com.study.bookspace.info.vo.AnswerVO;
import com.study.bookspace.info.vo.QnaVO;
import com.study.bookspace.util.PageVO;

public interface QnaService {
	
	//질문 작성
	void insertQna(QnaVO qnaVO);
	
	//문의사항 리스트 조회
	List<QnaVO> selectQna(PageVO pageVO);
	
	//전체 게시글 수 조회
	int selectQnaCnt();
	
	//문의글 상세 보기
	QnaVO qnaDetail(String qnaCode);

	//조회수 증가
	int updateQnaViewCnt(String qnaCode);
	
	int updateQna(QnaVO qnaVO);
	
	int deleteQna(String qnaCode);
	
	int updateIsAdminAnswer(String qnaCode);
	
	void insertQnaAnswer(AnswerVO answerVO);


}
