package com.study.bookspace.book.service;

import java.util.List;
import java.util.Map;

import com.study.bookspace.book.vo.BookVO;
import com.study.bookspace.book.vo.BorrowVO;
import com.study.bookspace.book.vo.CategoryVO;
import com.study.bookspace.book.vo.ImgVO;
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
	void borrowBook(BorrowVO borrowVO, int checkMem);
	
//	예약 삭제
	void deleteReserve(BorrowVO borrowVO);
	
//	현재 보유중인 책의 개수
	int getNowStockCnt(String bookCode);

//	예약 없이 대여 가능한 책의 개수 (모든사람)
	int getAbleBookCnt(String bookCode);
	
//	대여 가능한 회원인지의 여부
	int getAbleBorrowMem(Map<String, Object> searchMap);
	
	
//	예약하기 버튼 클릭 시, 대여한 회원인지 아닌지 확인 여부
	int getCheckBorrow(BorrowVO borrowVO);
	
//	도서 반납
	void returnBook(BorrowVO borrowVO);
	
//	도서 예약
	void reserveBook(ReserveVO reserveVO);
	
//	도서 예약 취소
	void delReserve(String bookCode);
	
//	도서 예약 ID
	String getReserveId(String bookCode);
	
//	도서관리) 도서 카테고리 조회
	List<CategoryVO> getCateListForAdmin();
	
//	도서관리) 도서 이미지 목록 조회
	List<ImgVO> getImgListForAdmin();
	
//	도서관리) 특정 도서 이미지, 도서 소개 조회
	BookVO getImgListForBook(String BookCode);
	
//	도서관리) 이미지 삭제
	int deleteImg(String bookImgCode);
	

//	도서관리) 도서 목록 조회
	List<BookVO> getBookListForAdminManage(BookVO bookVO);
	
//	도서관리) 나의 대여 관리
	List<BorrowVO> myBorrow(BorrowVO borrowVO);
	
//	도서관리) 도서 삭제
	void deleteBook(BookVO bookVO);
	
//	도서관리) 도서 수정
	void updateBook(BookVO bookVO);
	
//	도서관리) 도서 이미지, 소개 수정
	void updateBookDetail(BookVO bookVO);
	
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
	
//	페이징 처리 위한 전체 도서 개수
	int getBookCnt();
	
}
