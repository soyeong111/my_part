package com.study.bookspace.myBuy.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GoodsBuyVO extends SearchVO {
	private String buyCode;
	private String memId;
	private int buyPrice;
	private String buyDate;
	List<GoodsBuyDetailVO> buyDetailList;
	private int etc;
	private String orderNo;
}
