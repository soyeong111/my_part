package com.study.bookspace.room.service;

import java.util.List;

import com.study.bookspace.room.vo.SeatVO;
import com.study.bookspace.room.vo.SectionVO;
import com.study.bookspace.room.vo.UseVO;

public interface RoomService {
	
	//좌석 목록 조회
	List<SectionVO> getSectionList();
	
	//좌석 상세 조회
	SeatVO getSeatDetail(String seatCode);
	
	//좌석 상세 리스트 조회
	List<SeatVO> getSeatDetailList();
	
	//입실 버튼 클릭 시
	void getSeat(UseVO useVO);
	
	//이미 자리 사용중인 회원
	boolean isUsingSeat(String memId);
	
	//퇴실 버튼 클릭 시
	void checkOutSeat(String seatUseCode, String seatCode);
	
	//좌석 이용 내역 목록 조회
	List<UseVO> getSeatUseList();
	
	
}
