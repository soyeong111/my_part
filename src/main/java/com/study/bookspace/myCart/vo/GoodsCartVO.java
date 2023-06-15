package com.study.bookspace.myCart.vo;

import java.util.List;

import com.study.bookspace.goods.vo.GoodsVO;

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
	private GoodsVO goodsVO;
	
	private List<String>cartCodeList;
	
	
}
