package com.study.bookspace.goods.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GoodsCategoryVO {
	private int goodsCateNo;
	private String goodsCateStr;
	private String goodsCateIsUse;
	private int goodsCateOrderNum;
}
