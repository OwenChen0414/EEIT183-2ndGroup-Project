package com.ispan.controller.texts;

import java.io.IOException;

import com.ispan.dao.texts.TextsDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DeleteText")
public class DeleteText extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TextsDAO textsDAO = new TextsDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String textsId = request.getParameter("textsId");

		try {
			if (!textsDAO.existsById(textsId)) {
                request.setAttribute("message", "编号不存在，请输入正确的编号！");
                request.getRequestDispatcher("/dynamicView/texts/DeleteText.jsp").forward(request, response);
                return;
			}
			textsDAO.delete(textsId);
			response.sendRedirect("GetAllTexts");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException("数据删除失败", e);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
