package com.ispan.controller.texts;

import java.io.IOException;

import com.ispan.bean.texts.TextsBean;
import com.ispan.dao.texts.TextsDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/GetText")
public class GetText extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TextsDAO textsDAO = new TextsDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String textId = request.getParameter("textsId");
		try {
			TextsBean txt = textsDAO.get(textId);
			if (txt == null) {
                request.setAttribute("message", "编号不存在，请输入正确的编号！");
                request.getRequestDispatcher("dynamicView/texts/DeleteText.jsp").forward(request, response);
                return;
            }
			request.setAttribute("txt", txt);
			request.getRequestDispatcher("/dynamicView/texts/GetText.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
