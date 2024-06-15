package com.ispan.controller.playWithOthers;

import java.io.IOException;
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

@WebServlet("/DemoSelectOneUser")
public class DemoSelectOneUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		response.setContentType("text/html;charset=UTF-8");
		
		SessionFactory facotry = HibernateSession.getFactory();
		Session session = facotry.getCurrentSession();
		
		PwUserService pwUserService = new PwUserService(session);

		PwUser user = pwUserService.findUserId(Integer.parseInt(id));
	
		request.setAttribute("PwUser", user);
		request.getRequestDispatcher("/dynamicView/playWithOthers/OneUser.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}