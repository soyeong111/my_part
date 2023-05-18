package com.study.bookspace.book.vo;

import java.util.List;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookVO {

	private String booKCode;
	private String booKTitle;
	private String bookAuthor;
	private String bookPublicationDate;
	private String bookpublisher;
	private String bookIntro;
	private String bookCateNo;
	private String bookStockCnt;
	private String isbn;
	private List<ImgVO> imgList; // 상품 1개는 이미지 정보(ImgVO) 여러개를 갖고 있다
	private CategoryVO categoryVO; // 아이템정보 1개는 카테고리 정보를 갖고 있다
}

