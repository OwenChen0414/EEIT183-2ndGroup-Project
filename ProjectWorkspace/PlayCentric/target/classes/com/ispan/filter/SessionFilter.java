package com.ispan.filter;

import java.io.IOException;

import org.hibernate.Session;

import com.ispan.util.member.HibernateSession;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;

@WebFilter("/*")
public class SessionFilter extends HttpFilter implements Filter {
	private static final long serialVersionUID = 1L;

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try (HibernateSession hibernateSession = new HibernateSession()) {
			Session session = hibernateSession.getSession();
			try {
				chain.doFilter(request, response);
			} catch (Exception e) {
				session.getTransaction().rollback();
				System.out.println("交易取消");
				e.printStackTrace();
			}
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
