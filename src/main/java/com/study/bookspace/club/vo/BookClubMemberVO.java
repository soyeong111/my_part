package com.study.bookspace.club.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookClubMemberVO {
	private String club_code;
	private String mem_id;
	private String club_reg_date;
	private String club_mem_status;
}
