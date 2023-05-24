package com.study.bookspace.info.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.bookspace.info.vo.AnswerVO;
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

	@Override
	public QnaVO qnaDetail(String qnaCode) {
		return sqlSession.selectOne("qnaMapper.qnaDetail",qnaCode);
	}

	@Override
	public int updateQnaViewCnt(String qnaCode) {
		return sqlSession.update("qnaMapper.updateQnaViewCnt",qnaCode);
	}

	@Override
	public int updateQna(QnaVO qnaVO) {
		return sqlSession.update("qnaMapper.updateQna",qnaVO);
	}

	@Override
	public int deleteQna(String qnaCode) {
		return sqlSession.delete("qnaMapper.deleteQna", qnaCode);
	}

	@Override
	public int updateIsAdminAnswer(String qnaCode) {
		return sqlSession.update("qnaMapper.updateIsAdminAnswer", qnaCode);
	}


	public void insertQnaAnswer(AnswerVO answerVO) {
		sqlSession.insert("qnaMapper.insertQnaAnswer", answerVO);		
	}

}
