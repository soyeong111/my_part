package com.study.bookspace.room.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SeatListSearchVO {
	private String searchId;
	private String searchFromDate;
	private String searchToDate;
}
