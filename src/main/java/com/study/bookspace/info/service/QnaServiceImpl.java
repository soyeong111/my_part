package com.study.bookspace.info.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.bookspace.info.vo.QnaVO;
import com.study.bookspace.util.PageVO;

@Service("qnaService")
public class QnaServiceImpl implements QnaService {
	@Autowired
	private SqlSessionTemplate sqlSession;

	//질문 작성
	@Override
	public void insertQna(QnaVO qnaVO) {
		sqlSession.insert("qnaMapper.insertQna",qnaVO);
		
	}

	//문의사항 리스트 조회
	@Override
	public List<QnaVO> selectQna(PageVO pageVO) {
		return sqlSession.selectList("qnaMapper.selectQna",pageVO);
	}

	//문의사항 전체 데이터 조회
	@Override
	public int selectQnaCnt() {
		return sqlSession.selectOne("qnaMapper.selectQnaCnt");
	}


}
