package com.study.bookspace.book.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SearchBookVO {

	private String searchBookTitle;
	private String searchBookAuthor;
	private String searchPublicationDate;
	private String searchPublisher;
	private int[] searchBookCateNo;
	private String[] searchBookCateStr;
	private String searchIsbn;
	private String searchBookStatusStr;
	private int searchBookStatus;
	private String searchFromBookStock;
	private String searchToBookStock;
	private String searchOption;
	private String searchText;
	
}
