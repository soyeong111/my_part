package com.study.bookspace.book.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.bookspace.book.vo.BookVO;
import com.study.bookspace.book.vo.CategoryVO;

@Service("bookService")
public class BookServiceImpl implements BookService {

	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<CategoryVO> getCateListInUse() {
		return sqlSession.selectList("bookMapper.getCateListInUse");
	}

	@Override
	public List<BookVO> getBookListForUser() {
		return sqlSession.selectList("bookMapper.getBookListForUser");
	}
	
	
}
