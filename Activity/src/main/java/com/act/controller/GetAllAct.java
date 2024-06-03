package com.act.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.act.bean.ActivityBean;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/GetAllAct")
public class GetAllAct extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private DataSource dataSource;

    public GetAllAct() {
        try {
            Context context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/db20");
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<ActivityBean> activities = new ArrayList<>();
        String message = null;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Activity");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ActivityBean act = new ActivityBean();
                act.setActivityno(rs.getString("activityno"));
                act.setName(rs.getString("name"));
                act.setDescription(rs.getString("description"));
                act.setDate(rs.getString("date"));
                act.setLocation(rs.getString("location"));
                act.setOrganizer(rs.getString("organizer"));
                activities.add(act);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            message = "查詢失敗：" + e.getMessage();
        }

        request.setAttribute("activities", activities);
        if (message != null) {
            request.setAttribute("message", message);
        }
        // 將請求轉發到對應的 JSP 頁面
        request.getRequestDispatcher("/jsp/GetAllAct.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
