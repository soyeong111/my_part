package com.study.bookspace.goods.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GoodsSearchVO {
	private String searchGoodsName;
	private String[] searchCateCode;
	private int searchGoodsStatus;
	private String searchFromGoodsStock;
	private String searchToGoodsStock;

}
