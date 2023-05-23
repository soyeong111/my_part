package com.study.bookspace.book.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BorrowVO {

	private String borrowCode;
	private String bookCode;
	private String memId;
	private String borrowDate;
	private String returnDueDate;
	private String returnDate;
}

