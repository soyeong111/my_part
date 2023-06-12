package com.study.bookspace.room.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SeatVO {
	private String seatCode;
	private String sectionCode;
	private String seatName;
	private String seatIsUsed;
	private String memId;
	private String seatUseCode;
}
