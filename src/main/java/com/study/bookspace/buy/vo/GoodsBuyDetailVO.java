package com.study.bookspace.buy.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GoodsBuyDetailVO {
	private String buyDetailCode;
	private String buyCode;
	private String goodsCode;
	private int buyDetailCnt;
	private int buyDetailPrice;
	

}
