package com.study.bookspace.book.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReserveVO {
	private String bookReserveCode;
	private String bookCode;
	private String memId;
	private String bookReserveDate;
}
