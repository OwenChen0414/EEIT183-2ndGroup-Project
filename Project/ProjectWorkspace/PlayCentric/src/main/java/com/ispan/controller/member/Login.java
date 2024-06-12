package com.ispan.controller.member;

import java.io.IOException;

import org.hibernate.Session;

import com.ispan.bean.member.Member;
import com.ispan.dao.member.MemberDao;
import com.ispan.util.member.HibernateSession;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		if (account == null) {
			request.getRequestDispatcher("/dynamicView/member/Login.jsp")
			.forward(request, response);
		}
		Member member = new Member();
		member.setAccount(account);
		member.setPassword(password);

		{
			Session session = HibernateSession.getFactory().getCurrentSession();
			MemberDao memberDao = new MemberDao(session);
			member = memberDao.checkLogin(member);
		}
		HttpSession session = request.getSession();
		if (member == null) {
			request.setAttribute("role", "guest");
			request.getRequestDispatcher("/dynamicView/member/Login.jsp")
			.forward(request, response);
		}
//		member.setPassword("*****");
		session.setAttribute("member", member);
		response.sendRedirect("/PlayCentric/dynamicView/member/Home.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
