package com.ispan.controller.texts;


import java.io.IOException;

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

@WebServlet("/InsertText")
public class InsertText extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException { 
		
		SessionFactory facotry = HibernateSession.getFactory();
		Session session = facotry.getCurrentSession();
		TextsDAO textsDAO = new TextsDAO(session);
		TextsBean insertTxt = new TextsBean();
		insertTxt.setTextsReportId(request.getParameter("textsReportId"));
		insertTxt.setMembersId(request.getParameter("membersId"));
		insertTxt.setTalkId(request.getParameter("talkId"));
		insertTxt.setTagId(request.getParameter("tagId"));
		insertTxt.setForumId(request.getParameter("forumId"));
		insertTxt.setTitle(request.getParameter("title"));
		insertTxt.setTextContent(request.getParameter("textContent"));
		insertTxt.setUpdatedTime(request.getParameter("updatedTime").replace('T', ' '));
		insertTxt.setDoneTime(request.getParameter("doneTime").replace('T', ' '));
		

		try {
			textsDAO.insert(insertTxt);
			response.sendRedirect("GetAllTexts");
		} catch (Exception e) {
			request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("/dynamicView/texts/InsertText.jsp").forward(request, response);
		} 
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
