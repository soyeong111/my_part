package com.study.bookspace.info.service;

import java.util.List;

import com.study.bookspace.info.vo.AnswerVO;

public interface AnswerService {

	void insertAnswer(AnswerVO answerVO);
	
	List<AnswerVO> selectAnswer(String qnaCode);
	
	int updateAnswer(AnswerVO answerVO);
	
	int deleteAnswer(String answerCode);

}
