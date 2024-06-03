package com.act.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/UpdateAct")
public class UpdateAct extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String activityno = request.getParameter("activityno");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String date = request.getParameter("date");
        String location = request.getParameter("location");
        String organizer = request.getParameter("organizer");

        Connection conn = null;
        PreparedStatement stmt = null;
        String message = "";

        if (name == null && description == null && date == null && location == null && organizer == null) {
            request.getRequestDispatcher("/jsp/UpdateAct.jsp").forward(request, response);
            return;
        }

        try {
            Context context = new InitialContext();
            DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/db20");
            conn = ds.getConnection();
            String sql = "UPDATE Activity SET name = ?, description = ?, date = ?, location = ?, organizer = ? WHERE activityno = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, description);
            stmt.setString(3, date);
            stmt.setString(4, location);
            stmt.setString(5, organizer);
            stmt.setString(6, activityno);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                message = "修改成功";
                request.setAttribute("message", message);
            } else {
                message = "修改失敗：未進行任何更改";
                request.setAttribute("message", message);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "修改失敗：" + e.getMessage();
            request.setAttribute("message", message);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        request.getRequestDispatcher("/GetAllAct").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
