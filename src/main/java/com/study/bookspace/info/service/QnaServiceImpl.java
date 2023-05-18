package com.study.bookspace.info.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.bookspace.info.vo.QnaVO;

@Service("qnaService")
public class QnaServiceImpl implements QnaService {
	@Autowired
	private SqlSessionTemplate sqlSession;

	//질문 작성
	@Override
	public void insertQna(QnaVO qnaVO) {
		sqlSession.insert("qnaMapper.insertQna",qnaVO);
		
	}

}
