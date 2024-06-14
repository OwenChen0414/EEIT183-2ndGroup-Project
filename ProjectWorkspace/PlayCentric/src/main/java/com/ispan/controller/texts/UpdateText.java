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

@WebServlet("/UpdateText")
public class UpdateText extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SessionFactory factory = HibernateSession.getFactory();
		Session session = factory.getCurrentSession();
		TextsDAO textsDAO = new TextsDAO(session);
		TextsBean upadteTxt = new TextsBean();
		upadteTxt.setTextsId(request.getParameter("textsId"));
		upadteTxt.setTextsReportId(request.getParameter("textsReportId"));
		upadteTxt.setMembersId(request.getParameter("membersId"));
		upadteTxt.setTalkId(request.getParameter("talkId"));
		upadteTxt.setTagId(request.getParameter("tagId"));
		upadteTxt.setForumId(request.getParameter("forumId"));
		upadteTxt.setTitle(request.getParameter("title"));
		upadteTxt.setTextContent(request.getParameter("textContent"));
		upadteTxt.setUpdatedTime(request.getParameter("updatedTime").replace('T', ' '));
		upadteTxt.setDoneTime(request.getParameter("doneTime").replace('T', ' '));

		String textId = request.getParameter("textsId");
		try {
			textsDAO.update(upadteTxt);
			TextsBean txts = textsDAO.get(textId);
			if (txts == null) {
                request.setAttribute("message", "編號不存在，請輸入正確編號");
                request.getRequestDispatcher("/dynamicView/texts/DeleteText.jsp").forward(request, response);
                return;
            }
			response.sendRedirect("GetAllTexts");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
