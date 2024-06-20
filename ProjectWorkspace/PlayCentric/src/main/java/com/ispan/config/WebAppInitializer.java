package com.ispan.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import jakarta.servlet.Filter;
import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.ServletRegistration.Dynamic;

//相當於web.xml的Java程式組態類別
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	//用來註冊相當於beans.config.xml的Java程式組態類別
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {RootAppConfig.class};
	}

	//用來註冊相當於mvc-servlet.xml的Java程式組態類別
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {WebAppConfig.class};
	}

	//用來設定ServletMappings的url-pattern
	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}
	
	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter cef = new CharacterEncodingFilter("UTF-8", true);
		return new Filter[] {cef};
	}
	
	@Override
	protected void customizeRegistration(Dynamic registration) {
		registration.setMultipartConfig(new MultipartConfigElement("D:\\EEIT183-2ndGroup-Project\\ProjectWorkspace\\PlayCentric\\src\\main\\webapp\\WEB-INF\\resources\\images"));
	}

}
