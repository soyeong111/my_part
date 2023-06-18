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

	//알람 insert
	@Override
	public void insertAlram(AlramVO alramVO) {
		sqlSession.insert("alramMapper.insertAlram", alramVO);
	}

	//알람 갯수 조회
	@Override
	public int getAlramCnt(String memId) {
		return sqlSession.selectOne("alramMapper.getAlramCnt", memId);
	}

	//알람 목록 조회
	@Override
	public List<AlramVO> getAlramList(String memId) {
		return sqlSession.selectList("alramMapper.getAlramList", memId);
	}

	//알람 읽음
	@Override
	public void updateAlramACheck(AlramVO alramVO) {
		sqlSession.update("alramMapper.updateAlramACheck", alramVO);
	}

	//알람 삭제
	@Override
	public void deleteAlram(String alramCode) {
		sqlSession.delete("alramMapper.deleteAlram", alramCode);
	}

	//알람 상세 조회
	@Override
	public AlramVO getAlramDetail(String alramCode) {
		return sqlSession.selectOne("alramMapper.getAlramDetail", alramCode);
	}

	
	
}
