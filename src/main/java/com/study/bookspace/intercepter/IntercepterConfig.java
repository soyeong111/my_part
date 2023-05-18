package com.study.bookspace.intercepter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class IntercepterConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(getPublicHeaderMenuIntercepter())
			.addPathPatterns("/")
			.excludePathPatterns("/**/*Ajax");
		
//		registry.addInterceptor(getMenuIntercepter())
//			.order(2)
//			.addPathPatterns("/admin/**")
//			.excludePathPatterns("/admin/deleteCategory")
//			.excludePathPatterns("/admin/updateItem")
//			.excludePathPatterns("/admin/regItemInsert")
//			.excludePathPatterns("/**/*Ajax");
		
	}
	
	@Bean
	public PublicHeaderMenuIntercepter getPublicHeaderMenuIntercepter() {
		return new PublicHeaderMenuIntercepter();
	}
	
//	@Bean
//	public MenuIntercepter getMenuIntercepter() {
//		return new MenuIntercepter();
//	}

}
