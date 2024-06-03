package com.ispanwei.crud;

import java.io.IOException;
import java.sql.Connection;
import javax.naming.InitialContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.ispanwei.bean.EmpBean;

import javax.naming.Context;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/SelectAll")
public class SelectAll extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
		String selectAll = "SELECT * FROM employee ";
		Connection conn;
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
			conn = ds.getConnection();

			PreparedStatement stmt = conn.prepareStatement(selectAll);
			ResultSet rs = stmt.executeQuery();
			
			List<EmpBean> emps = new ArrayList<>();
			EmpBean emp = new EmpBean();
			while (rs.next()) {
				emp = new EmpBean();
				emp.setEmpno(rs.getString(1));
				emp.setEname(rs.getString(2));
				emp.setHiredate(rs.getString(3));
				emp.setSalary(rs.getString(4));
				emp.setDeptno(rs.getString(5));
				emp.setTitle(rs.getString(6));
				emps.add(emp);
			}
			request.setAttribute("emps", emps);
			stmt.close();
			request.getRequestDispatcher("/CRUD/SelectAll.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
