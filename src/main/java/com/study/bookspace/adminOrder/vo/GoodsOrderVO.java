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
	private String orderNo;
	private int buyPrice;
	

}
