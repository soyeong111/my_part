package com.study.bookspace.room.vo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class SectionVO {
	private String sectionCode;
	private String sectionName;
	private List<SeatVO> seatList;
}
