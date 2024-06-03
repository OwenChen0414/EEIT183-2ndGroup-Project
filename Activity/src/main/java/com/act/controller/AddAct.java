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

@WebServlet("/AddAct")
public class AddAct extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String activityno = request.getParameter("activityno");
        if (activityno == null) {
            request.getRequestDispatcher("/jsp/AddAct.jsp").forward(request, response);
            return;
        }

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String date = request.getParameter("date");
        String location = request.getParameter("location");
        String organizer = request.getParameter("organizer");

        Connection conn = null;
        PreparedStatement stmt = null;
        String message = "";
        try {
            Context context = new InitialContext();
            DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/db20");
            conn = ds.getConnection();
            String sql = "INSERT INTO Activity (activityno, name, description, date, location, organizer) VALUES (?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, activityno);
            stmt.setString(2, name);
            stmt.setString(3, description);
            stmt.setString(4, date);
            stmt.setString(5, location);
            stmt.setString(6, organizer);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                message = "新增成功";
            } else {
                message = "新增失敗";
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "新增失敗: " + e.getMessage();
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
