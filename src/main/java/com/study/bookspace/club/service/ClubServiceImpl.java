package com.study.bookspace.club.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("clubService")
public class ClubServiceImpl implements ClubService{
	@Autowired
	private SqlSessionTemplate sqlSession;

}
