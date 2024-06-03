package com.act.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DeleteAct")
public class DeleteAct extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String activityNo = request.getParameter("activityno");
        if (activityNo == null || activityNo.trim().isEmpty()) {
            request.getRequestDispatcher("/jsp/DeleteAct.jsp").forward(request, response);
            return;
        }

        Connection conn = null;
        PreparedStatement stmt = null;
        String message = "";

        try {
            Context context = new InitialContext();
            DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/db20");
            conn = ds.getConnection();
            String sql = "DELETE FROM Activity WHERE activityno = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, activityNo);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                message = "刪除成功";
            } else {
                message = "刪除失敗：請重新嘗試";
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "刪除失敗：" + e.getMessage();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        request.setAttribute("message", message);
        request.getRequestDispatcher("/GetAllAct").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
