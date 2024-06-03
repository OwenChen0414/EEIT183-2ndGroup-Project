package com.ispanwei.crud;

import java.io.IOException;
import java.sql.Connection;
import javax.naming.InitialContext;
import java.sql.PreparedStatement;
import javax.sql.DataSource;

import javax.naming.Context;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



@WebServlet("/DeleteOneEmp2")
public class DeleteOneEmp2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String empno = request.getParameter("empno");
		String deleteOne = "DELETE FROM employee WHERE empno = ?";
		Connection conn ;
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
			conn = ds.getConnection();

			PreparedStatement stmt = conn.prepareStatement(deleteOne);
			stmt.setString(1, empno);
			stmt.executeUpdate();

			request.getRequestDispatcher("/CRUD/DeleteOneEmp1.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
