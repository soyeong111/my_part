package com.study.bookspace.myBook.vo;

import com.study.bookspace.util.PageVO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookRecordSearchVO extends PageVO {
	
	private String searchColumn;
	private String searchValue;
	private String searchOrder;
	private String searchMemId;
	
	@Override
	public String toString() {
		return "BookRecordSearchVO [searchColumn=" + searchColumn + ", searchValue=" + searchValue + ", searchOrder="
				+ searchOrder + ", searchMemId=" + searchMemId + ", toString()=" + super.toString() + "]";
	}

}
