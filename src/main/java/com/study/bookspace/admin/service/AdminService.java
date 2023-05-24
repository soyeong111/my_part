package com.study.bookspace.admin.service;

import java.util.List;

import com.study.bookspace.admin.vo.MainMenuVO;

public interface AdminService {
	
	// ROLE별 사용중인 전체 메뉴 목록
	List<MainMenuVO> getMenuListByRoleAndUse(String mainMenuRole);
	
	// ROLE별 사용중인 메인 메뉴 목록
	List<MainMenuVO> getMainMenuListByRoleAndUse(String mainMenuRole);

}
