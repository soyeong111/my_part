package com.study.bookspace.room.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UseVO extends SeatListSearchVO{
	private String seatUseCode;
	private String seatCode;
	private String memId;
	private String seatInDate;
	private String seatOutDate;
}
