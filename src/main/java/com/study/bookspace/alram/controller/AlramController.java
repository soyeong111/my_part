package com.study.bookspace.alram.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.bookspace.alram.service.AlramService;
import com.study.bookspace.alram.vo.AlramVO;

import jakarta.annotation.Resource;

@Controller
@RequestMapping("/alram")
public class AlramController {
	@Resource(name = "alramService")
	private AlramService alramService;
	
	
	//알람 목록 조회 Ajax
	@ResponseBody
	@RequestMapping("/alramListAjax")
	public List<AlramVO> alramListAjax(Authentication authentication) {
		
		User user = (User)authentication.getPrincipal();
		String memId = user.getUsername();
		
		return alramService.getAlramList(memId);
	}
	
	
	//알림삭제 Ajax
	@ResponseBody
	@RequestMapping("/deleteAlramAjax")
	public void deleteAlramAjax(String alramCode) {
		alramService.deleteAlram(alramCode);
	}
	
	//알람 상세 조회 Ajax
	@ResponseBody
	@RequestMapping("/getAlramMessageAjax")
	public AlramVO getAlramMessageAjax(String alramCode, AlramVO alramVO) {
		alramService.updateAlramACheck(alramVO);
		return alramService.getAlramDetail(alramCode);
	}
	
	
	
	
}
