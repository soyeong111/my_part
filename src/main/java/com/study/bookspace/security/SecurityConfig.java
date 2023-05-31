package com.study.bookspace.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
		security.csrf().disable()
				.authorizeHttpRequests()
					.requestMatchers("/"
									, "/accessDeny"
									, "/book/bookList"
									, "/book/newBook"
									, "/book/bestBook"
									, "/book/bookDetail"
									, "/club/clubInfo"
									, "/club/club"
									, "/club/clubDetail"
									, "/goods/goodsList"
									, "/info/libraryIntro"
									, "/info/qna"
									, "/info/qnaDetail"
									, "/info/wayToLibrary"
									, "/info/searchQna"
									, "/room/readingRoom").permitAll()
					.requestMatchers("/admin/**").hasRole("ADMIN")
					.requestMatchers("/member/**").hasRole("ANONYMOUS")
					.anyRequest().authenticated()
				.and()
					.formLogin()
					.loginPage("/member/loginForm")
					.loginProcessingUrl("/member/login")
					.usernameParameter("memId")
					.passwordParameter("memPw")
					.successHandler(getSucessHandler())
					.failureHandler(getFailureHandler())
				.and()
					.logout()
					.logoutUrl("/member/logout")
					.invalidateHttpSession(true)
					.logoutSuccessUrl("/")
				.and()
					.exceptionHandling()
					.accessDeniedPage("/accessDeny");
		return security.build();
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().requestMatchers("/js/**", "/css/**", "/image/**");
	}
	
	@Bean
	public FailureHandler getFailureHandler() {
		return new FailureHandler();
	}
	
	@Bean
	public SucessHandler getSucessHandler() {
		return new SucessHandler();
	}
	
}
