package com.study.bookspace.intercepter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class IntercepterConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
//		registry.addInterceptor(getMenuIntercepter())
//			.order(2)
//			.addPathPatterns("/admin/**")
//			.excludePathPatterns("/admin/deleteCategory")
//			.excludePathPatterns("/admin/updateItem")
//			.excludePathPatterns("/admin/regItemInsert")
//			.excludePathPatterns("/**/*Ajax");
//		
//		registry.addInterceptor(getCategoryInterceptor())
//			.addPathPatterns("/item/**")
//			.addPathPatterns("/cart/**")
//			.addPathPatterns("/buy/**")
//			.addPathPatterns("/member/loginForm")
//			.excludePathPatterns("/**/*Ajax")
//			.excludePathPatterns("/cart/deleteCart")
//			.excludePathPatterns("/cart/updateCartCnt");
		
	}
	
//	@Bean
//	public MenuIntercepter getMenuIntercepter() {
//		return new MenuIntercepter();
//	}
//	
//	@Bean
//	public CategoryInterceptor getCategoryInterceptor() {
//		return new CategoryInterceptor();
//	}

}
