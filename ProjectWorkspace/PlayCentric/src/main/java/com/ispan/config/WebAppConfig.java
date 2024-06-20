package com.ispan.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

//相當於mvc-servlet.xml程式組態類別
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.ispan"})
public class WebAppConfig implements WebMvcConfigurer {
	
	@Bean
	public MappingJackson2JsonView jsonView() {
		MappingJackson2JsonView jaJsonView = new MappingJackson2JsonView();
		jaJsonView.setPrettyPrint(true);
		return jaJsonView;
	}
	
	@Bean
	public ContentNegotiatingViewResolver cnViewResolver() {
		ContentNegotiatingViewResolver cnViewResolver = new ContentNegotiatingViewResolver();
		List<View> lists = new ArrayList<View>();
		lists.add(jsonView());
		cnViewResolver.setDefaultViews(lists);
		return cnViewResolver;
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/", "Home");
//		registry.addViewController("/playforfun.do").setViewName("uploadFile123");
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**").addResourceLocations("/WEB-INF/resources/images/");
		registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/resources/css/");
		registry.addResourceHandler("/fonts/**").addResourceLocations("/WEB-INF/resources/fonts/");
		registry.addResourceHandler("/javascript/**").addResourceLocations("/WEB-INF/resources/javascript/");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		lci.setParamName("locale");
		registry.addInterceptor(lci).addPathPatterns("/**");
	}
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	@Bean
	public InternalResourceViewResolver irViewResolver() {
		InternalResourceViewResolver irv = new InternalResourceViewResolver();
		irv.setPrefix("/WEB-INF/pages/");
		irv.setSuffix(".jsp");
		irv.setOrder(6);
		return irv;
	}
	
//	@Bean
//	public ResourceBundleMessageSource messageSource() {
//		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//		messageSource.setBasename("i18n.message");
//		messageSource.setDefaultEncoding("UTF-8");
//		return messageSource;
//	}
	
	@Bean
	public SessionLocaleResolver localeResolver() {
		return new SessionLocaleResolver();
	}
	
	@Bean
	public StandardServletMultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}
	
}
