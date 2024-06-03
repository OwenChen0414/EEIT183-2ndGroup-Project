package com.ispanwei.crud;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/CreateOneEmp")
public class CreateOneEmp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String empno = request.getParameter("empno");
		String ename = request.getParameter("ename");
		String hiredate = request.getParameter("hiredate");
		String salary = request.getParameter("salary");
		String deptno = request.getParameter("deptno");
		String title = request.getParameter("title");

		String sql = "INSERT INTO employee (empno,ename, hiredate, salary, deptno, title) VALUES (?,?, ?, ?, ?, ?)";

		Connection conn = null;
		Context context = null;
		PreparedStatement stmt = null;
		try {
			context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
			conn = ds.getConnection();

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, empno);
			stmt.setString(2, ename);
			stmt.setString(3, hiredate);
			stmt.setString(4, salary);
			stmt.setString(5, deptno);
			stmt.setString(6, title);
			stmt.executeUpdate();

			request.getRequestDispatcher("/CRUD/CreateOneEmp.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				conn.close();
				stmt.close();
				context.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}
}