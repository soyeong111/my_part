package com.study.bookspace.myBuy.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SearchVO {
	private int month;
	private String fromDate;
	private String toDate;
}
