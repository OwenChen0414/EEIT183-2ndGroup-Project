package com.ispan.controller.playWithOthers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ispan.dao.playWithOthers.PwUserService;
import com.ispan.util.member.HibernateSession;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DemoDeletePwUser")
public class DemoDeletePwUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");

		SessionFactory facotry = HibernateSession.getFactory();
		Session session = facotry.getCurrentSession();

		PwUserService pwUserService = new PwUserService(session);
		pwUserService.deleteUser(Integer.parseInt(id));

	request.getRequestDispatcher("/DemoPwFindAll").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

//	@Override
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		processAction(request, response);
//	}
//
//	@Override
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		processAction(request, response);
//	}
//
//	private void processAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		response.setContentType("text/html;charset=UTF-8");
//		PrintWriter out = response.getWriter();
//
//		SessionFactory facotry = HibernateUtil.getSessionFactory();
//		Session session = facotry.getCurrentSession();
//
//		PwUserService pwUserService = new PwUserService(session);
//		int deleteId = 1;
//		pwUserService.deleteUser(deleteId);
//		System.out.println("刪除成功");
//
//		out.close();
//	}

}
