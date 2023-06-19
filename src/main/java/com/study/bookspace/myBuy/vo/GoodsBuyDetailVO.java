package com.study.bookspace.myBuy.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.study.bookspace.goods.vo.GoodsVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GoodsBuyDetailVO {
	
	private String buyDetailCode;
	
	@JsonProperty("goods_code") 
	private String goodsCode;
	
	@JsonProperty("buy_detail_cnt") 
	private int buyDetailCnt;
	
	 @JsonProperty("buy_detail_price") 
	private int buyDetailPrice;

	
	private String buyCode;
	
	private GoodsVO goodsVO;
	

}
