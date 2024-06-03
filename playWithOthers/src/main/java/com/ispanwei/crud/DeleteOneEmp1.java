package com.ispanwei.crud;

import java.io.IOException;
import java.sql.Connection;
import javax.naming.InitialContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import com.ispanwei.bean.EmpBean;

import javax.naming.Context;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/DeleteOneEmp1")
public class DeleteOneEmp1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String empno = request.getParameter("empno");
		String selectOne = "SELECT * FROM employee WHERE empno = ?";
		Connection conn ;
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
			conn = ds.getConnection();
			
			PreparedStatement stmt = conn.prepareStatement(selectOne);
			stmt.setString(1, empno);
			ResultSet rs = stmt.executeQuery();
			EmpBean emp = new EmpBean();
			if (rs.next()) {
				emp.setEmpno(rs.getString(1));
				emp.setEname(rs.getString(2));
				emp.setHiredate(rs.getString(3));
				emp.setSalary(rs.getString(4));
				emp.setDeptno(rs.getString(5));
				emp.setTitle(rs.getString(6));
			}
			request.setAttribute("emp", emp);
			
			HttpSession session = request.getSession();
			session.setAttribute("empno", empno);
		
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
