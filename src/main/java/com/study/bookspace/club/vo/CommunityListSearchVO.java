package com.study.bookspace.club.vo;

import com.study.bookspace.util.PageVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class CommunityListSearchVO extends PageVO{
	private String searchTitle;
	private String searchWriter;
	private String searchOption;
	private String searchText;
	@Override
	public String toString() {
		return "CommunityListSearchVO [searchTitle=" + searchTitle + ", searchWriter=" + searchWriter
				+ ", searchOption=" + searchOption + ", searchText=" + searchText + ", toString()=" + super.toString()
				+ "]";
	}
	
	
	
}
