package com.texts.controller;

import java.io.IOException;

import com.texts.bean.TextsBean;
import com.texts.dao.TextsDAO;

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
                request.getRequestDispatcher("jsp/DeleteText.jsp").forward(request, response);
                return;
            }
			request.setAttribute("txt", txt);
			request.getRequestDispatcher("/jsp/GetText.jsp").forward(request, response);
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
