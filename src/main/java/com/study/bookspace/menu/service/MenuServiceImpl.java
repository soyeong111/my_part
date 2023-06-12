package com.study.bookspace.menu.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.bookspace.menu.vo.MainMenuVO;

@Service("menuService")
public class MenuServiceImpl implements MenuService {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	// ROLE별 사용중인 전체 메뉴 목록
	@Override
	public List<MainMenuVO> getMenuListByRoleAndUse(String mainMenuRole) {
		return sqlSession.selectList("menuMapper.getMenuListByRoleAndUse", mainMenuRole);
	}

	// ROLE별 사용중인 메인 메뉴 목록
	@Override
	public List<MainMenuVO> getMainMenuListByRoleAndUse(String mainMenuRole) {
		return sqlSession.selectList("menuMapper.getMainMenuListByRoleAndUse", mainMenuRole);
	}

}
