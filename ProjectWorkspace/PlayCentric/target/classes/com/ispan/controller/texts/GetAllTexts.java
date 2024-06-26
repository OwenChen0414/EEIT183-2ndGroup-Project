package com.ispan.controller.texts;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ispan.bean.texts.TextsBean;
import com.ispan.dao.texts.TextsDAO;
import com.ispan.util.member.HibernateSession;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/GetAllTexts")
public class GetAllTexts extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	
	private TextsDAO textsDAO;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		SessionFactory facotry = HibernateSession.getFactory();
	    Session session = facotry.getCurrentSession();
	    textsDAO = new TextsDAO(session);
		try {
			List<TextsBean> txts = textsDAO.getAll();
			request.setAttribute("txts", txts);
			request.getRequestDispatcher("/dynamicView/texts/GetAllTexts.jsp").forward(request, response);
			Collections.sort(txts, new Comparator<TextsBean>() {
				@Override
				public int compare(TextsBean t1, TextsBean t2) {
					return t1.getTextsId().compareTo(t2.getTextsId());
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
