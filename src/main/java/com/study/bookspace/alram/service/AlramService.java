package com.study.bookspace.alram.service;

import java.util.List;

import com.study.bookspace.alram.vo.AlramVO;

public interface AlramService {
	
	//클럽 신청 알림
	void insertAlram(AlramVO alramVO);
	
	//알람 갯수 조회
	int getAlramCnt(String memId);
	
	//알람 목록 조회
	List<AlramVO> getAlramList(String memId);
	
	//알람 상세 조회
	AlramVO getAlramDetail(String alramCode);
	
	//알람 체크 변경
	void updateAlramACheck(AlramVO alramVO);
	
	//알람 삭제
	void deleteAlram(String alramCode);
}
