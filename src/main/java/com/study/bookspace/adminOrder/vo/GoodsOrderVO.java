package com.study.bookspace.adminOrder.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GoodsOrderVO {
	private String orderCode;
	private String buyDate;
	private String memId;
	private int orderNo;
	private String orderStr;
	private int buyPrice;
	private String buyCode;

}
