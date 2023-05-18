package com.study.bookspace.admin.service;

import java.util.List;

import com.study.bookspace.admin.vo.MainMenuVO;

public interface AdminService {
	
	// ROLE별 메뉴 목록
	List<MainMenuVO> getMenuListByRole(List<String> roleList);

}
