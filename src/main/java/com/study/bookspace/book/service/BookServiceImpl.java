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
import com.study.bookspace.book.vo.ImgVO;
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

	// 도서 대여
	@Override
	@Transactional(rollbackFor = Exception.class) 
	public void borrowBook(BorrowVO borrowVO, int checkMem) {
		sqlSession.insert("bookMapper.borrowBook", borrowVO);
		sqlSession.update("bookMapper.updateBorrowCnt", borrowVO);
		if(checkMem == 1) {
			sqlSession.delete("bookMapper.deleteReserve", borrowVO);
			sqlSession.update("bookMapper.downgradeReserveCnt", borrowVO);
		}
		sqlSession.update("bookMapper.borrowStatus", borrowVO);
	}

//	도서 반납
	@Override
	@Transactional(rollbackFor = Exception.class) 
	public String returnBook(BorrowVO borrowVO) {
		sqlSession.update("bookMapper.returnBook", borrowVO);
		sqlSession.update("bookMapper.updateReturnCnt", borrowVO);
		sqlSession.update("bookMapper.borrowStatus", borrowVO);
		return sqlSession.selectOne("bookMapper.alramId", borrowVO);
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

//	도서 예약
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void reserveBook(ReserveVO reserveVO) {
		sqlSession.insert("bookMapper.reserveBook", reserveVO);
		sqlSession.update("bookMapper.updateReserveCnt", reserveVO);
		sqlSession.update("bookMapper.borrowStatus", reserveVO);
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
	public List<ImgVO> getImgListForAdmin() {
		return sqlSession.selectList("bookMapper.getImgListForAdmin");
	}

	@Override
	public BookVO getImgListForBook(String bookCode) {
		return sqlSession.selectOne("bookMapper.getImgListForBook", bookCode);
	}


	@Override
	public List<BorrowVO> myBorrow(BorrowVO borrowVO) {
		return sqlSession.selectList("bookMapper.myBorrow", borrowVO);
	}

	@Override
	public List<ReserveVO> myReserve(ReserveVO reserveVO) {
		return sqlSession.selectList("bookMapper.myReserve", reserveVO);
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

	@Override
	public int deleteImg(String bookImgCode) {
		return sqlSession.delete("bookMapper.deleteImg", bookImgCode);
	}


	@Override
	public void delReserve(String bookCode) {
		    sqlSession.delete("bookMapper.delReserve", bookCode);
	}

	@Override
	public String getReserveId(String bookCode) {
		return sqlSession.selectOne("bookMapper.getReserveId", bookCode);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateBookDetail(BookVO bookVO) {
		sqlSession.update("bookMapper.updateBookDetail", bookVO);
		sqlSession.insert("bookMapper.insertImges", bookVO);
	}

	@Override
	public int getBookCnt() {
		return sqlSession.selectOne("bookMapper.getBookCnt");
	}

	@Override
	public int getNowStockCnt(String bookCode) {
		return sqlSession.selectOne("bookMapper.getNowStockCnt", bookCode);
	}

	@Override
	public int getAbleBookCnt(String bookCode) {
		return sqlSession.selectOne("bookMapper.getAbleBookCnt", bookCode);
	}

	@Override
	@Transactional
	public int getAbleBorrowMem(Map<String, Object> searchMap) {
		return sqlSession.selectOne("bookMapper.getAbleBorrowMem", searchMap);
	}

	@Override
	public int getCheckBorrow(BorrowVO borrowVO) {
		return sqlSession.selectOne("bookMapper.getCheckBorrow", borrowVO);
	}


//	예약 취소
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void cancelReserve(ReserveVO reserveVO) {
		sqlSession.delete("bookMapper.deleteReserve", reserveVO);
		sqlSession.update("bookMapper.downgradeReserveCnt", reserveVO);
		sqlSession.update("bookMapper.borrowStatus", reserveVO);
	}

	@Override
	public List<BorrowVO> borrowManage() {
		return sqlSession.selectList("bookMapper.borrowManage");
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void overDue(BorrowVO borrowVO) {
		sqlSession.update("bookMapper.overDue", borrowVO);
		sqlSession.update("bookMapper.overDueMem", borrowVO);
	}

	@Override
	public List<ReserveVO> reserveManage() {
		return sqlSession.selectList("bookMapper.reserveManage");
	}

	@Override
	public List<Map<String, Object>> cateBorrow() {
		return sqlSession.selectList("bookMapper.cateBorrow");
	}











	

}
	
	
