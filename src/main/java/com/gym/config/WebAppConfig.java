package com.gym.config;

import java.util.ArrayList;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.gym.loginInterceptor.LoginInterceptor;
@Configuration @EnableWebMvc @ComponentScan("com.gym")  @EnableAspectJAutoProxy
public class WebAppConfig implements WebMvcConfigurer {
	@Autowired
	LoginInterceptor  loginInterceptor;
	
	
	@Bean
	public ViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource resource = new ResourceBundleMessageSource();
		resource.setBasename("messages");
		return resource;
	}
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**")
		.addResourceLocations("/WEB-INF/views/css/");
		registry.addResourceHandler("/images/**")
		.addResourceLocations("/WEB-INF/views/images/");
		registry.addResourceHandler("/js/**")
		.addResourceLocations("/WEB-INF/views/js/");
		registry.addResourceHandler("/fonts/**")
		.addResourceLocations("/WEB-INF/views/fonts/");
		registry.addResourceHandler("/webfonts/**")
		.addResourceLocations("/WEB-INF/views/webfonts/");
	}
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setDefaultEncoding("UTF-8");
		resolver.setMaxUploadSize(81920000);
		return resolver;
	}
	@Bean
	public MappingJackson2JsonView jsonView() {
		MappingJackson2JsonView view = new MappingJackson2JsonView();
		view.setPrettyPrint(true); //JSON的部分則無需特別設定，僅設定一屬性prettyPrint，使印出JSON格式較為可讀，
		return view;
	}
	//讓視圖可以傳出json格式
	@Bean
	public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
		ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
		resolver.setContentNegotiationManager(manager);
		ArrayList<View> views = new ArrayList<>(); 
		views.add(jsonView());
		resolver.setDefaultViews(views);
		return resolver;
	}
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		WebMvcConfigurer.super.addInterceptors(registry);
		
		registry.addInterceptor(loginInterceptor).addPathPatterns("/*").excludePathPatterns("/login")
		.excludePathPatterns("/register").excludePathPatterns("/forgotPassword")
		.excludePathPatterns("/memberCheckAjex").excludePathPatterns("/memberActivate").excludePathPatterns("/")
		.excludePathPatterns("/registerResult").excludePathPatterns("/buyProduct").excludePathPatterns("/showFavorite").excludePathPatterns("/productDisplay");
		
	}
	
}
