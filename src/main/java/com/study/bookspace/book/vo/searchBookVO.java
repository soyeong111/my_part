package com.study.bookspace.book.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class searchBookVO {

	private String searchItemName;
	private String[] searchCateCode;
	private int searchItemStatus;
	private String searchFromItemStock;
	private String searchToItemStock;
	
}
