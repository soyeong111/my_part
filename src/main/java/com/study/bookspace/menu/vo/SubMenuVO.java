package com.study.bookspace.menu.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SubMenuVO {
	
	private String subMenuCode;
	private String subMenuName;
	private String subMenuUrl;
	private String mainMenuCode;
	private int subMenuOrder;
	private String subMenuIsUse;

}
