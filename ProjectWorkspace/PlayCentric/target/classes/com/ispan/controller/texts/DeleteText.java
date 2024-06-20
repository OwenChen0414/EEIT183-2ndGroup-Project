package com.ispan.controller.texts;

import java.io.IOException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ispan.dao.texts.TextsDAO;
import com.ispan.util.member.HibernateSession;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DeleteText")
public class DeleteText extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private TextsDAO textsDAO;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		SessionFactory facotry = HibernateSession.getFactory();
		Session session = facotry.getCurrentSession();
		textsDAO = new TextsDAO(session);
		String textsId = request.getParameter("textsId");

		try {
			if (!textsDAO.existsById(textsId)) {
                request.setAttribute("message", "編號不存在，請輸入正確編號");
                request.getRequestDispatcher("/dynamicView/texts/DeleteText.jsp").forward(request, response);
                return;
			}
			textsDAO.delete(textsId);
			response.sendRedirect("GetAllTexts");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException("數據刪除失敗", e);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
