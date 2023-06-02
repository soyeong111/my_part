package com.study.bookspace.buy.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GoodsBuyVO {
	private String buyCode;
	private String memId;
	private int buyPrice;
	private String buyDate;
}
