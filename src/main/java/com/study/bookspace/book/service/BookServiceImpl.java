package com.study.bookspace.book.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Override
	public String getNextBookCode() {
		return sqlSession.selectOne("bookMapper.getNextBookCode");
	}

	@Override
	@Transactional(rollbackFor =  Exception.class)
	public void regBook(BookVO bookVO) {
		sqlSession.insert("bookMapper.regBook", bookVO);
		sqlSession.insert("bookMapper.insertImges", bookVO);
	}

	@Override
	public BookVO getBookDetail(String bookCode) {
		return sqlSession.selectOne("bookMapper.getBookDetail", bookCode);
	}
	
	
	
	
}
