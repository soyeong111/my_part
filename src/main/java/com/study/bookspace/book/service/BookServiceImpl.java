package com.study.bookspace.book.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.bookspace.book.vo.BookVO;
import com.study.bookspace.book.vo.BorrowVO;
import com.study.bookspace.book.vo.CategoryVO;
import com.study.bookspace.book.vo.ReserveVO;
import com.study.bookspace.book.vo.SearchBookVO;

@Service("bookService")
public class BookServiceImpl implements BookService {

	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<CategoryVO> getCateListInUse() {
		return sqlSession.selectList("bookMapper.getCateListInUse");
	}

	@Override
	public List<BookVO> getBookListForUser(SearchBookVO searchBookVO) {
		return sqlSession.selectList("bookMapper.getBookListForUser", searchBookVO);
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

	
	@Override
	@Transactional(rollbackFor = Exception.class) 
	public void returnBook(BorrowVO borrowVO) {
		sqlSession.update("bookMapper.returnBook", borrowVO);
		sqlSession.update("bookMapper.updateReturnCnt", borrowVO);
	}

	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void extendBorrow(BorrowVO borrowVO) {
		sqlSession.update("bookMapper.extendBorrow", borrowVO);
	}

	@Override
	public int checkBorrowStatus(BorrowVO borrowVO) {
	    return sqlSession.selectOne("bookMapper.checkBorrowStatus", borrowVO);
	}

	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void reserveBook(ReserveVO reserveVO) {
		sqlSession.insert("bookMapper.reserveBook", reserveVO);
		sqlSession.update("bookMapper.updateReserveCnt", reserveVO);
	}
	
//	@Override
//	public Map<String, Object> getBorrowAndStockCnt(String bookCode) {
//		return sqlSession.selectOne("bookMapper.getBorrowAndStockCnt", bookCode);
//	}


	@Override
	public int getBorrowLimit(BorrowVO borrowVO) {
		return sqlSession.selectOne("bookMapper.getBorrowLimit", borrowVO);
	}
	
	@Override
	public int checkReserveStatus(ReserveVO reserveVO) {
		return sqlSession.selectOne("bookMapper.checkReserveStatus", reserveVO);
	}

	@Override
	public int getReserveLimit(ReserveVO reserveVO) {
		return sqlSession.selectOne("bookMapper.getReserveLimit", reserveVO);
	}
	
	@Override
	public int checkReserveBeforeExtend(ReserveVO reserveVO) {
		return sqlSession.selectOne("bookMapper.checkReserveBeforeExtend", reserveVO);
	}


	@Override
	public List<BookVO> getBookListForAdminManage(BookVO bookVO) {
		return sqlSession.selectList("bookMapper.getBookListForAdminManage", bookVO);
	}

	@Override
	public List<CategoryVO> getCateListForAdmin() {
		return sqlSession.selectList("bookMapper.getCateListForAdmin");
	}

	@Override
	public List<BorrowVO> myBorrow(BorrowVO borrowVO) {
		return sqlSession.selectList("bookMapper.myBorrow", borrowVO);
	}

	@Override
	public void deleteBook(BookVO bookVO) {
		sqlSession.delete("bookMapper.deleteBook", bookVO);
	}

	@Override
	public void updateBook(BookVO bookVO) {
		sqlSession.update("bookMapper.updateBook", bookVO);
	}

	@Override
	public String getReturnDuedate(String borrowCode) {
		return sqlSession.selectOne("bookMapper.getReturnDuedate", borrowCode);
	}









	

}
	
	
