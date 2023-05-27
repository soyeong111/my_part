package com.study.bookspace.book.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SearchBookVO {

	private String searchBookTitle;
	private String searchAuthor;
	private String searchPublicationDate;
	private String searchPublisher;
	private int[] searchBookCateNo;
	private String searchIsbn;
	private int searchBookStatus;
	private String searchFromBookStock;
	private String searchToBookStock;
}
