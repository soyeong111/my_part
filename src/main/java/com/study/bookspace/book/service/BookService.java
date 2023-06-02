package com.study.bookspace.book.service;

import java.util.List;
import java.util.Map;

import com.study.bookspace.book.vo.BookVO;
import com.study.bookspace.book.vo.BorrowVO;
import com.study.bookspace.book.vo.CategoryVO;
import com.study.bookspace.book.vo.ReserveVO;
import com.study.bookspace.book.vo.SearchBookVO;


public interface BookService {
   
//	사용중인 카테고리 목록 조회
	List<CategoryVO> getCateListInUse();
	
//	도서 목록 조회
	List<BookVO> getBookListForUser(SearchBookVO searchBookVO);
	
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
	
//	도서 반납
	void returnBook(BorrowVO borrowVO);
	
//	도서 예약
	void reserveBook(ReserveVO reserveVO);
	
//	도서관리) 도서 카테고리 조회
	List<CategoryVO> getCateListForAdmin();

//	도서관리) 도서 목록 조회
	List<BookVO> getBookListForAdminManage(BookVO bookVO);
	
//	도서관리) 나의 대여 관리
	List<BorrowVO> myBorrow(BorrowVO borrowVO);
	
//	도서관리) 도서 삭제
	void deleteBook(BookVO bookVO);
	
//	도서관리) 도서 수정
	void updateBook(BookVO bookVO);
	
////	도서 대여 개수
//	Map<String, Object> getBorrowAndStockCnt(String bookCode);
	
	
//	중복 대여
	int checkBorrowStatus(BorrowVO borrowVO);
	
// 	대여 개수 확인
	int getBorrowLimit(BorrowVO borrowVO);
	
//	중복 예약
	int checkReserveStatus(ReserveVO reserveVO);
	
//	예약 개수 확인
	int getReserveLimit(ReserveVO reserveVO);
	
//	도서 연장
	void extendBorrow(BorrowVO borrowVO);

//	반납 기한 연장 전, 예약 여부 확인
	int checkReserveBeforeExtend(ReserveVO reserveVO);
	
//	반납 기한 연장 후, 연장된 반납기한
	String getReturnDuedate(String borrowCode);
	
}
