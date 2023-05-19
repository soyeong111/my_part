package com.study.bookspace.club.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookClubMemberVO {
	private String clubCode;
	private String memId;
	private String clubRegDate;
	private String clubMemStatus;
}
