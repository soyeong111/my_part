package com.study.bookspace.book.service;

import java.util.List;
import java.util.Map;

import com.study.bookspace.book.vo.BookVO;
import com.study.bookspace.book.vo.BorrowVO;
import com.study.bookspace.book.vo.CategoryVO;


public interface BookService {
   
//	사용중인 카테고리 목록 조회
	List<CategoryVO> getCateListInUse();
	
//	도서 목록 조회
	List<BookVO> getBookListForUser();
	
//	신작 도서 조회
	List<BookVO> getNewBookList();
	
//	베스트 셀러 조회
	List<BookVO> getBestBookList();

//	다음 등록될 상품 코드 조회
	String getNextBookCode();
	
//	도서 등록
	void regBook(BookVO bookVO);
	
//	도서 상세 조회
	BookVO getBookDetail(String bookCode);
	
//	도서 대여
	void borrowBook(BorrowVO borrowVO);
	
//	도서관리) 도서 카테고리 조회
	List<CategoryVO> getCateListForAdmin();

//	도서관리) 도서 목록 조회
	List<BookVO> getBookListForAdminManage(BookVO bookVO);
	
////	도서 대여 개수
//	Map<String, Object> getBorrowAndStockCnt(String bookCode);
	
//	도서 대여 개수 확인
	int checkBorrowStatus(BorrowVO borrowVO);
	
// 	대여 개수 확인
	int getBorrowLimit(BorrowVO borrowVO);


	
}
