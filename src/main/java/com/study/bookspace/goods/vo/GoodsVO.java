package com.study.bookspace.goods.vo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GoodsVO {
	private String goodsCode;
	private String goodsCateCode;
	private int goodsNum;
	private String goodsName;
	private int goodsPrice;
	private int goodsStockCnt;
	private String goodsIntro;
	private int goodsStatus;
	private String goodsStatusStr;
	private int rowNum;
	private int rowNumber;
	private int bestBuycnt;
	private String goodsRegDate;
	
	
	//이미지
	private List<GoodsImgVO> goodsImgList;
	
	//카테고리
	private GoodsCategoryVO goodsCategoryVO;
	
	//서치
	private GoodsSearchVO goodsSearchVO;
	
}
