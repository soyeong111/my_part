package com.study.bookspace.myBook.vo;

import com.study.bookspace.book.vo.BookVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookRecordVO {
	
	private String recordCode;
	private String memId;
	private String bookCode;
	private String startDate;
	private String endDate;
	private String regDate;
	private String bookReview;
	private BookVO bookVO;

}
