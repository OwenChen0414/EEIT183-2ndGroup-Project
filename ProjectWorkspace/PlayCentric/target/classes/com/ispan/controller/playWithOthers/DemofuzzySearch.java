package com.ispan.controller.playWithOthers;

import java.io.IOException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ispan.bean.playWithOthers.PwUser;
import com.ispan.dao.playWithOthers.PwUserService;
import com.ispan.util.member.HibernateSession;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DemofuzzySearch")
public class DemofuzzySearch extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nickname = request.getParameter("nickname");

		response.setContentType("text/html;charset=UTF-8");
		SessionFactory facotry = HibernateSession.getFactory();
		Session session = facotry.getCurrentSession();

		PwUserService pwUserService = new PwUserService(session);
		
		List<PwUser> pwUsers = pwUserService.findUserName(nickname);
		
		request.setAttribute("pwUsers", pwUsers);
		
		request.getRequestDispatcher("/dynamicView/playWithOthers/GetAllUser.jsp").forward(request, response);
	}
		
		


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}