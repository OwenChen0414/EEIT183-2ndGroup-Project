package com.ispan.filter;

import java.io.IOException;

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
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		HttpSession session = httpServletRequest.getSession();
		String lastPage = httpServletRequest.getHeader("Referer");
		if (lastPage!=null) {
			lastPage = lastPage.substring(lastPage.lastIndexOf("member/")+7);
			System.out.println("lastPage = "+lastPage);
			if (!lastPage.contains("Login")) {
				System.out.println("lastPage => "+lastPage);
				session.setAttribute("lastPage", lastPage);
			}
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
