package com.study.bookspace.book.service;

import java.util.List;

import com.study.bookspace.book.vo.BookVO;
import com.study.bookspace.book.vo.CategoryVO;


public interface BookService {
   
//	사용중인 카테고리 목록 조회
	List<CategoryVO> getCateListInUse();
	
//	도서 목록 목록 조회
	List<BookVO> getBookListForUser();

//	다음 등록될 상품 코드 조회
	String getNextBookCode();
	
//	도서 등록
	void regBook(BookVO bookVO);
	
}
