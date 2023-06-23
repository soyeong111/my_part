package com.study.bookspace.book.vo;

import java.util.Arrays;

import com.study.bookspace.util.BookPageVO;
import com.study.bookspace.util.PageVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter

public class SearchBookVO  extends BookPageVO{

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
	@Override
	public String toString() {
		return "SearchBookVO [searchBookTitle=" + searchBookTitle + ", searchBookAuthor=" + searchBookAuthor
				+ ", searchPublicationDate=" + searchPublicationDate + ", searchPublisher=" + searchPublisher
				+ ", searchBookCateNo=" + Arrays.toString(searchBookCateNo) + ", searchBookCateStr="
				+ Arrays.toString(searchBookCateStr) + ", searchIsbn=" + searchIsbn + ", searchBookStatusStr="
				+ searchBookStatusStr + ", searchBookStatus=" + searchBookStatus + ", searchFromBookStock="
				+ searchFromBookStock + ", searchToBookStock=" + searchToBookStock + ", searchOption=" + searchOption
				+ ", searchText=" + searchText + ", toString()=" + super.toString() + "]";
	}
	
}
