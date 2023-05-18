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

	// ROLE별 메뉴 목록
	@Override
	public List<MainMenuVO> getMenuListByRole(List<String> roleList) {
		return sqlSession.selectList("adminMapper.getMenuListByRole", roleList);
	}

}
