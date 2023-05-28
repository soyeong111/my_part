package com.study.bookspace.member.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.study.bookspace.member.vo.MemberVO;

import jakarta.annotation.Resource;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Resource(name = "memberService")
	private MemberService memberService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberVO userInfo = memberService.getUserInfoForLogin(username);
		if (userInfo == null) {
			throw new UsernameNotFoundException("조회된 회원 없음");
		}
		UserDetails user = User.withUsername(userInfo.getMemId())
								.password(userInfo.getMemPw())
								.roles(userInfo.getMemRole())
								.build();
		return user;
	}

}
