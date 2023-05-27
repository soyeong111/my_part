package com.study.bookspace.book.vo;

import java.util.List;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookVO {

	private String bookCode;
	private String bookTitle;
	private String bookAuthor;
	private String bookPublicationDate;
	private String bookPublisher;
	private String bookIntro;
	private int bookCateNo;
	private String bookStockCnt;
	private String isbn;
	private BorrowVO borrowVO;
	private int borrowCnt;
	private int reserveCnt;
	private int availableStockCnt;
	private List<ImgVO> imgList; // 상품 1개는 이미지 정보(ImgVO) 여러개를 갖고 있다
	private CategoryVO categoryVO; // 책정보 1개는 카테고리 정보를 갖고 있다
	private SearchBookVO searchBookVO;  // bookVO 1개는 검색조건 1개를 갖고 있다
	private BookStatusVO bookStatusVO; 
	
}


