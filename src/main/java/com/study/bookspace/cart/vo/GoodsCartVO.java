package com.study.bookspace.cart.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GoodsCartVO {
	private String cartCode;
	private String goodsCode;
	private String memId;
	private String cartRegDate;
	private int cartRegCnt;
	private int totalPrice;
	
}
