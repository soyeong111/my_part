package com.study.bookspace.myBook.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("myBookService")
public class MyBookServiceImpl implements MyBookService {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

}
