package com.study.bookspace.intercepter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class IntercepterConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(getPublicMenuIntercepter())
			.addPathPatterns("/"
							, "/accessDeny"
							, "/info/**"
							, "/book/**"
							, "/room/**"
							, "/club/**"
							, "/goods/**"
							, "/cart/**"
							, "/buy/**"
							, "/member/**")
			.excludePathPatterns("/**/*Ajax"
								, "/book/regBookProcess"
								, "/club/regClub"
								, "/club/regBoard"
								, "/club/updateBoard"
								, "/club/deleteBoard"
								, "/club/regReply"
								, "/club/updateClub"
								, "/club/deleteClub"
								, "/club/updateReply"
								, "/club/deleteReply"
								, "/info/sendQuestion"
								, "/info/updateQna"
								, "/info/deleteQna"
								, "/info/sendAnswer"
								, "/info/updateAnswer"
								, "/info/deleteAnswer"
								, "/info/qnaAnswer"
								, "/room/checkOutSeat");
		
		registry.addInterceptor(getMyMenuIntercepter())
			.addPathPatterns("/mMember/**"
							, "/mBook/**"
							, "/mClub/**"
							, "/mGoods/**")
			.excludePathPatterns("/**/*Ajax");
		
		registry.addInterceptor(getAdminMenuIntercepter())
			.addPathPatterns("/aBook/**"
							, "/aClub/**"
							, "/aGoods/**"
							, "/aMember/**"
							, "/aLibrary/**")
			.excludePathPatterns("/**/*Ajax");
		
	}
	
	@Bean
	public PublicMenuIntercepter getPublicMenuIntercepter() {
		return new PublicMenuIntercepter();
	}
	
	@Bean
	public MyMenuIntercepter getMyMenuIntercepter() {
		return new MyMenuIntercepter();
	}
	
	@Bean
	public AdminMenuIntercepter getAdminMenuIntercepter() {
		return new AdminMenuIntercepter();
	}

}
