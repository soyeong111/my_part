package com.study.bookspace.book.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.bookspace.book.vo.BookVO;
import com.study.bookspace.book.vo.BorrowVO;
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
	public List<BookVO> getNewBookList() {
		return sqlSession.selectList("bookMapper.getNewBookList");
	}
	
	@Override
	public List<BookVO> getBestBookList() {
		return sqlSession.selectList("bookMapper.getBestBookList");
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

	@Override
	@Transactional(rollbackFor = Exception.class) 
	public void borrowBook(BorrowVO borrowVO) {
		sqlSession.insert("bookMapper.borrowBook", borrowVO);
		sqlSession.update("bookMapper.updateBorrowCnt", borrowVO);
	}

//	@Override
//	public Map<String, Object> getBorrowAndStockCnt(String bookCode) {
//		return sqlSession.selectOne("bookMapper.getBorrowAndStockCnt", bookCode);
//	}

	@Override
	public int checkBorrowStatus(BorrowVO borrowVO) {
	    return sqlSession.selectOne("bookMapper.checkBorrowStatus", borrowVO);
	}

	@Override
	public int getBorrowLimit(BorrowVO borrowVO) {
		return sqlSession.selectOne("bookMapper.getBorrowLimit", borrowVO);
	}





	

}
	
	
