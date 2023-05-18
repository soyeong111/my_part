package com.study.bookspace.admin.vo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MainMenuVO {
	
	private String mainMenuCode;
	private String mainMenuName;
	private String mainMenuUrl;
	private String mainMenuRole;
	private int mainMenuOrder;
	private List<SubMenuVO> subMenuList;

}
