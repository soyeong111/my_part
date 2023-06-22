package com.study.bookspace.info.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.bookspace.info.vo.AnswerVO;
import com.study.bookspace.info.vo.NoticeVO;

@Service("answerService")
public class AnswerServiceImpl implements AnswerService{
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public void insertAnswer(AnswerVO answerVO) {
		sqlSession.insert("qnaAnswerMapper.insertAnswer",answerVO);
	}

	@Override
	public List<AnswerVO> selectAnswer(String qnaCode) {
		return sqlSession.selectList("qnaAnswerMapper.selectAnswer",qnaCode);
	}

	@Override
	public int updateAnswer(AnswerVO answerVO) {
		return sqlSession.update("qnaAnswerMapper.updateAnswer",answerVO);	
	}
	
	@Override
	public int deleteAnswer(String answerCode) {
		return sqlSession.delete("qnaAnswerMapper.deleteAnswer",answerCode);
	}


	@Override
	public List<NoticeVO> noticeForPublic(NoticeVO noticeVO) {
		return sqlSession.selectList("noticeMapper.noticeForPublic", noticeVO);
	}

	@Override
	public NoticeVO noticeDetailForPublic(String noticeCode) {
		return sqlSession.selectOne("noticeMapper.noticeDetailForPublic",noticeCode);
	}

	@Override
	public void insertNotice(NoticeVO noticeVO) {
		sqlSession.insert("noticeMapper.insertNotice",noticeVO);
	}

	@Override
	public int deleteNotice(String noticeNo) {
		return sqlSession.delete("noticeMapper.deleteNotice",noticeNo);
	}

	@Override
	public int updateNotice(NoticeVO noticeVO) {
		return sqlSession.update("noticeMapper.updateNotice", noticeVO);
	}



	

}
