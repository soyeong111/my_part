package com.study.bookspace.book.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ImgVO {
	
	private String bookImgCode;
	private String bookCode;
	private String originFileName;
	private String attachedFileName;
	private String isMainImg;
	private String bookIntro;
}



