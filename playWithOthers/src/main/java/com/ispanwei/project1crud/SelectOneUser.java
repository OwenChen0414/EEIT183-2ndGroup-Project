package com.ispanwei.project1crud;



import java.io.IOException;
import java.sql.Connection;
import javax.naming.InitialContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import com.ispanwei.bean.EmpBean;
import com.ispanwei.bean.PlayUserBean;

import javax.naming.Context;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/SelectOneUser")
public class SelectOneUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		PwDAO pwDAO = new PwDAO();
		PlayUserBean user = null;
		try {
			user = pwDAO.getOneUser(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("user", user);
		request.getRequestDispatcher("/Project1/SelectOneUser.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}