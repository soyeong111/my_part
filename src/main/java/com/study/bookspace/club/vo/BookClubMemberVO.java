package com.study.bookspace.club.vo;

import com.study.bookspace.member.vo.MemberVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookClubMemberVO {
	private String acceptCode;
	private String clubCode;
	private String memId;
	private String clubRegDate;
	private int clubMemStatus;
	private String clubRole;
	private String memStatus;
	private BookClubVO bookClubVO;
	private MemberVO memberVO;
}
