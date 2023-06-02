package com.study.bookspace.room.service;

import java.util.List;

import com.study.bookspace.room.vo.SeatVO;
import com.study.bookspace.room.vo.SectionVO;
import com.study.bookspace.room.vo.UseVO;

public interface RoomService {
	
	//좌석 목록 조회
	List<SectionVO> getSectionList();
	
	//좌석 상세 조회
	String getSeatDetail(String seatCode);
}