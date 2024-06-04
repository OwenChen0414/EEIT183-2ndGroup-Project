package com.ispan.controller.activity;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.ispan.bean.activity.ActivityBean;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/GetAct")
public class GetAct extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String searchQuery = request.getParameter("searchQuery");
        String searchCriteria = request.getParameter("searchCriteria");
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<ActivityBean> activities = new ArrayList<>();
        String message = null;

        if (searchQuery == null || searchQuery.trim().isEmpty()) {
            forwardRequest(request, response, activities, null);
            return;
        }

        try {
            Context context = new InitialContext();
            DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/db20");
            conn = ds.getConnection();

            // 根據不同的查詢條件構建 SQL 查詢語句
            String sql = "";
            switch (searchCriteria) {
                case "activityno":
                    sql = "SELECT * FROM Activity WHERE activityno LIKE ?";
                    break;
                case "name":
                    sql = "SELECT * FROM Activity WHERE name LIKE ?";
                    break;
                case "description":
                    sql = "SELECT * FROM Activity WHERE description LIKE ?";
                    break;
                case "date":
                    sql = "SELECT * FROM Activity WHERE date LIKE ?";
                    break;
                case "location":
                    sql = "SELECT * FROM Activity WHERE location LIKE ?";
                    break;
                case "organizer":
                    sql = "SELECT * FROM Activity WHERE organizer LIKE ?";
                    break;
                default:
                    // 預設使用活動編號作為查詢條件
                    sql = "SELECT * FROM Activity WHERE activityno LIKE ?";
            }

            stmt = conn.prepareStatement(sql);
            String queryParam = "%" + searchQuery + "%";
            stmt.setString(1, queryParam);
            rs = stmt.executeQuery();

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

            if (activities.isEmpty()) {
                message = "查無符合的活動資料";
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "查詢失敗：" + e.getMessage();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        forwardRequest(request, response, activities, message);
    }

    private void forwardRequest(HttpServletRequest request, HttpServletResponse response, List<ActivityBean> activities, String message)
            throws ServletException, IOException {
        request.setAttribute("activities", activities);
        if (message != null) {
            request.setAttribute("message", message);
        }
        request.getRequestDispatcher("/dynamicView/activity/GetAct.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
