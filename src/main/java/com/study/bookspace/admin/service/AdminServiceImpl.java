package com.study.bookspace.admin.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("adminService")
public class AdminServiceImpl {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

}
