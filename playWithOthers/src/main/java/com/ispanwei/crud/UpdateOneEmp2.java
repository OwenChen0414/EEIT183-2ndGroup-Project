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

@WebServlet("/UpdateOneEmp2")
public class UpdateOneEmp2 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String empno = request.getParameter("empno");
        String ename = request.getParameter("ename");
        String hiredate = request.getParameter("hiredate");
        String salary = request.getParameter("salary");
        String deptno = request.getParameter("deptno");
        String title = request.getParameter("title");

        String sql = "UPDATE employee SET ename = ?, hiredate = ?, salary = ?, deptno = ?, title = ? WHERE empno = ?";

        try {
            Context context = new InitialContext();
            DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
            Connection conn = ds.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, ename);
            stmt.setString(2, hiredate);
            stmt.setString(3, salary);
            stmt.setString(4, deptno);
            stmt.setString(5, title);
            stmt.setString(6, empno);
            stmt.executeUpdate();
            stmt.close();
            conn.close();

            // 重新導向到員工列表頁面
            response.sendRedirect("SelectAll");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}