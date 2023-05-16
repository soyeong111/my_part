package com.study.bookspace.goods.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GoodsVO {
	private String goodsCode;
	private String goodsName;
	private int goodsPrice;
	private int goodsStockCnt;
	private String goodsRegDate;
	private String goodsIntro;
	private int goodsStatusNo;
	private int goodsCateNo;
	private GoodsCategoryVO goodsCategoryVO;

}
