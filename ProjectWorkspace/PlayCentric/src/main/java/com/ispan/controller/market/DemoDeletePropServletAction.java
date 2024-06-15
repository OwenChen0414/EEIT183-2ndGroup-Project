package com.ispan.controller.market;

import java.io.IOException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.ispan.dao.market.PropService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.ispan.util.member.HibernateSession;

@WebServlet("/DemoDeletePropServletAction")
public class DemoDeletePropServletAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		insertProp(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		insertProp(request, response);
	}

	private void insertProp(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		Transaction transaction = null;
		int propId = Integer.valueOf(request.getParameter("propId"));

		SessionFactory factory = HibernateSession.getFactory();
		Session session = factory.getCurrentSession();
		PropService pService = new PropService(session);
		boolean status = pService.deleteById(propId);
		System.out.println("delete status:" + status);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/DemoPropServletAction");
		dispatcher.forward(request, response);

	}
}
