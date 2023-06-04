package com.study.bookspace.room.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.bookspace.room.vo.SeatVO;
import com.study.bookspace.room.vo.SectionVO;
import com.study.bookspace.room.vo.UseVO;

@Service("roomService")
public class RoomServiceImpl implements RoomService{
	@Autowired
	private SqlSessionTemplate sqlSession;

	//좌석 목록 조회
	@Override
	public List<SectionVO> getSectionList() {
		return sqlSession.selectList("roomMapper.getSectionList");
	}

	//좌석 상세 조회
	@Override
	public SeatVO getSeatDetail(String seatCode) {
		return sqlSession.selectOne("roomMapper.getSeatDetail", seatCode);
	}

	

}
