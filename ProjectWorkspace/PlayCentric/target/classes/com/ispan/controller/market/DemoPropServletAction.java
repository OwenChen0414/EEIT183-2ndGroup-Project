package com.ispan.controller.market;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.RequestDispatcher;

import java.io.IOException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ispan.bean.market.Prop;
import com.ispan.dao.market.PropService;
import com.ispan.util.member.HibernateSession;

@WebServlet("/DemoPropServletAction")
public class DemoPropServletAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		selectedFindAllProps(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		selectedFindAllProps(request, response);
	}

	private void selectedFindAllProps(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		SessionFactory factory = HibernateSession.getFactory();
		Session session = null;

		int gameId = Integer.valueOf(request.getParameter("gameId"));

		session = factory.getCurrentSession();
		PropService pService = new PropService(session);
		List<Prop> props = pService.findSelectedProps(gameId);
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("props", props);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/dynamicView/market/propSheet.jsp");
		dispatcher.forward(request, response);

	}
}
