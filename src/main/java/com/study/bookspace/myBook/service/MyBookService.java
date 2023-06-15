package com.study.bookspace.myBook.service;

import java.util.List;
import java.util.Map;

import com.study.bookspace.myBook.vo.BookRecordVO;

public interface MyBookService {
	
	// 최근 3개월 대여 도서 제목 목록
	List<Map<String, String>> getBookTitleListThreeMonths(String memId);
	
	// 독서 기록 등록
	void regBookRecord(BookRecordVO bookRecordVO);

}
