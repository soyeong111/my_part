package com.study.bookspace.menu.service;

import java.util.List;

import com.study.bookspace.menu.vo.MainMenuVO;

public interface MenuService {
	
	// ROLE별 사용중인 전체 메뉴 목록
	List<MainMenuVO> getMenuListByRoleAndUse(String mainMenuRole);
	
	// ROLE별 사용중인 메인 메뉴 목록
	List<MainMenuVO> getMainMenuListByRoleAndUse(String mainMenuRole);

}
