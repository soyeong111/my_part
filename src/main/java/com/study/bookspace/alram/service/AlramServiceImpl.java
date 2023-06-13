package com.study.bookspace.alram.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.bookspace.alram.vo.AlramVO;

@Service("alramService")
public class AlramServiceImpl implements AlramService{
	@Autowired
	private SqlSessionTemplate sqlSession;

	//클럽 신청 알림 insert
	@Override
	public void insertAlram(AlramVO alramVO) {
		sqlSession.insert("alramMapper.insertAlram", alramVO);
	}

	//알람 갯수 조회
	@Override
	public int getAlramCnt(String memId) {
		return sqlSession.selectOne("alramMapper.getAlramCnt", memId);
	}

	//알람 읽음 N -> Y
	@Override
	public void readAlram(String alramCode) {
		sqlSession.update("alramMapper.readAlram", alramCode);
	}

	//알람 목록 조회
	@Override
	public List<AlramVO> getAlramList(String memId) {
		return sqlSession.selectList("alramMapper.getAlramList", memId);
	}
	
	
}
