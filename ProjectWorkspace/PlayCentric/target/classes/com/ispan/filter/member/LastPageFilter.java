package com.ispan.filter.member;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@WebFilter("/*")
public class LastPageFilter extends HttpFilter implements Filter {
	private static final long serialVersionUID = 1L;

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("filter.run()");
		List<String> URL_LIST = Arrays.asList("Login","UpdateMember","InsertMember","DelectMember");
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		String lastPage = httpServletRequest.getHeader("Referer");
		if (lastPage!=null) {
			lastPage = lastPage.substring(lastPage.lastIndexOf("member/")+7);
			System.out.println("lastPage = "+lastPage);
			if (!URL_LIST.contains(lastPage)) {
				System.out.println("lastPage => "+lastPage);
				HttpSession session = httpServletRequest.getSession();
				session.setAttribute("lastPage", lastPage);
			}
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
