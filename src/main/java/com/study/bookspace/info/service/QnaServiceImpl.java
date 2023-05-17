package com.study.bookspace.info.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("qnaService")
public class QnaServiceImpl implements QnaService {
	@Autowired
	private SqlSessionTemplate sqlSession;

	//질문 작성
	@Override
	public void insertQna() {
		sqlSession.insert("qnaMpper.insertQna");
		
	}

}
