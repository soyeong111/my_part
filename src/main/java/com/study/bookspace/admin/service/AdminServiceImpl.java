package com.study.bookspace.admin.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.bookspace.admin.vo.MainMenuVO;

@Service("adminService")
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	// ROLE별 사용중인 전체 메뉴 목록
	@Override
	public List<MainMenuVO> getMenuListByRoleAndUse(String mainMenuRole) {
		return sqlSession.selectList("adminMapper.getMenuListByRoleAndUse", mainMenuRole);
	}

	// ROLE별 사용중인 메인 메뉴 목록
	@Override
	public List<MainMenuVO> getMainMenuListByRoleAndUse(String mainMenuRole) {
		return sqlSession.selectList("adminMapper.getMainMenuListByRoleAndUse", mainMenuRole);
	}

}
