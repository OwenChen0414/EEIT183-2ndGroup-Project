package com.ispan.controller.texts;


import java.io.IOException;
import com.ispan.bean.texts.TextsBean;
import com.ispan.dao.texts.TextsDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/InsertText")
public class InsertText extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TextsDAO textsDAO = new TextsDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		TextsBean txt = new TextsBean();
		txt.setTextsId(request.getParameter("textsId"));
		txt.setTextsReportId(request.getParameter("textsReportId"));
		txt.setMembersId(request.getParameter("membersId"));
		txt.setTalkId(request.getParameter("talkId"));
		txt.setTagId(request.getParameter("tagId"));
		txt.setForumId(request.getParameter("forumId"));
		txt.setTitle(request.getParameter("title"));
		txt.setTextContent(request.getParameter("textContent"));
		txt.setUpdatedTime(request.getParameter("updatedTime").replace('T', ' '));
		txt.setDoneTime(request.getParameter("doneTime").replace('T', ' '));

		try {
			textsDAO.insert(txt);
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
