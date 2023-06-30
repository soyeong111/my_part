package com.study.bookspace.club.vo;

import java.util.List;

import com.study.bookspace.member.vo.MemberVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookClubVO {
	private String clubCode;
	private String clubName;
	private String clubIntro;
	private String clubCreateDate;
	private int clubMemCnt;
	private int clubStatusNo;
	private String memId;
	private String clubShortInfo;
	private String thisBookCode;
	private BookClubImageVO bookClubImageVO;
	private BookClubMemberVO bookClubMemberVO;
	private CommunityVO communityVO;
	private MemberVO memberVO;
}
