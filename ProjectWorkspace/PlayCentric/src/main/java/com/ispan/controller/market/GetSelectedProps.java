package com.ispan.controller.market;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import com.ispan.bean.market.PropBean;
import com.ispan.dao.market.PropDao;
import com.ispan.util.market.JndiToJdbc;

@WebServlet("/GetSelectedProps")

public class GetSelectedProps extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		game參數為selceted時所設的session，在update時調用game會是空值，所以另外寫gameIdForUpdate讓update調用
		String selectedGameIdCheck = request.getParameter("gameId");
		int selectedGameId = 0;
		if (selectedGameIdCheck != null) {
			selectedGameId = Integer.valueOf(request.getParameter("gameId"));
		} else {
			selectedGameId = Integer.valueOf(request.getParameter("gameIdForUpdate"));
		}
//        response.getWriter().write(selectedGameId);
		List props;
		try {
			props = PropDao.getSelectedProps(selectedGameId);
			HttpSession session = request.getSession();
			session.setAttribute("selectedGameIdForMarket", selectedGameIdCheck);
			session.setAttribute("props", props);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/dynamicView/market/getAllProps.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
