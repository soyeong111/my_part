package com.study.bookspace.book.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReserveVO {
	private String reserveCode;
	private String bookCode;
	private String memId;
	private String reserveDate;
	private String bookTitle;
	private String reserveDueDate;
	private String bookAlramDate;
}
