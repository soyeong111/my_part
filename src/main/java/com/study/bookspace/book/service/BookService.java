package com.study.bookspace.book.service;

import java.util.List;

import com.study.bookspace.book.vo.BookVO;
import com.study.bookspace.book.vo.CategoryVO;


public interface BookService {
   
	//사용중인 카테고리 목록 조회
	List<CategoryVO> getCateListInUse();
	
	//사용자 상품 목록 조회
	List<BookVO> getBookListForUser();
	
	
}
