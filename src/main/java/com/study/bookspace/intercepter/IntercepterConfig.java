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
							, "/info/**"
							, "/book/**"
							, "/room/**"
							, "/club/**"
							, "/goods/**"
							, "/cart/**"
							, "/buy/**"
							, "/member/**")
			.excludePathPatterns("/**/*Ajax"
								, "/info/sendQuestion"
								, "/book/regBookProcess"
								, "/club/regClub"
								, "/club/regBoard"
								, "/club/updateBoard"
								, "/club/deleteBoard"
								, "/club/regReply"
								, "/info/sendQuestion"
								, "/info/qnaAnswer");
		
		registry.addInterceptor(getMyMenuIntercepter())
			.addPathPatterns("/my/**");
		
		registry.addInterceptor(getAdminMenuIntercepter())
		.addPathPatterns("/admin/**");
		
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
