package com.study.bookspace.myBook.service;

import java.util.List;
import java.util.Map;

import com.study.bookspace.book.vo.BookVO;
import com.study.bookspace.myBook.vo.BookRecordSearchVO;
import com.study.bookspace.myBook.vo.BookRecordVO;

public interface MyBookService {
	
	// 독서 기록 등록
	void regBookRecord(BookRecordVO bookRecordVO);
	
	// 독서 기록 목록
	List<BookRecordVO> getMyBookRecord(BookRecordSearchVO bookRecordSearchVO);
	
	//독서 기록 삭제
	int deleteBookRecord(String recordCode);
	
	// 독서 기록 수정
	int updateBookRecord(BookRecordVO bookRecordVO);
	
	// 독서 기록 개수 조회
	int getBookRecordDataCnt(BookRecordSearchVO bookRecordSearchVO);
	
	// 도서 검색 모달
	List<BookVO> bookSearchForModal(Map<String, Object> mapData);

}
