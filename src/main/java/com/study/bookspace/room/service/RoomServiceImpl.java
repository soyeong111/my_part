package com.study.bookspace.room.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.bookspace.club.vo.BookClubMemberVO;
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

	@Override
	public List<SeatVO> getSeatDetailList() {
		return sqlSession.selectList("roomMapper.getSeatDetailList");
	}

	//입실 버튼 클릭 시
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void getSeat(UseVO useVO) {
		sqlSession.insert("roomMapper.getSeat", useVO);
		sqlSession.update("roomMapper.updateSeatY", useVO.getSeatCode());
		
	}

	//이미 자리 사용중인 회원 확인
	@Override
	public boolean isUsingSeat(String memId) {
		int result = sqlSession.selectOne("roomMapper.isUsingSeat", memId);
		return result != 0 ? true : false;
	}

	//퇴실 버튼 클릭
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void checkOutSeat(String seatUseCode, String seatCode) {
		sqlSession.update("roomMapper.checkOutSeat", seatUseCode);
		sqlSession.update("roomMapper.updateSeatN", seatCode);
	}

	// 좌석 이용 내역 목록 조회
	@Override
	public List<UseVO> getSeatUseList() {
		return sqlSession.selectList("roomMapper.getSeatUseList");
	}

	//좌석 이용 내역 조회
	@Override
	public UseVO getSeatUseDetail(String seatUseCode) {
		return sqlSession.selectOne("roomMapper.getSeatUseDetail", seatUseCode);
	}

	//해당 좌석 사용중인 사용자 아이디 조회
	@Override
	public String getSeatUseId(String seatCode) {
		return sqlSession.selectOne("roomMapper.getSeatUseId", seatCode);
	}


}
