package com.study.bookspace.club.vo;

import com.study.bookspace.util.PageVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class CommunityListSearchVO extends PageVO{
	private String searchKeyword;
	private String searchValue;
	@Override
	public String toString() {
		return "CommunityListSearchVO [searchKeyword=" + searchKeyword + ", searchValue=" + searchValue
				+ ", toString()=" + super.toString() + "]";
	}
	
	
}
