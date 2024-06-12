package com.ispan.controller.playWithOthers;



import java.io.IOException;

import com.ispan.bean.playWithOthers.PlayUserBean;
import com.ispan.dao.playWithOthers.PwDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/fuzzySearch")
public class fuzzySearch extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nickname = request.getParameter("nickname");
		PwDAO pwDAO = new PwDAO();
		PlayUserBean user = null;
		try {
			user = pwDAO.fuzzySearch(nickname);
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("user", user);
		request.getRequestDispatcher("/dynamicView/playWithOthers/SelectOneUser.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}