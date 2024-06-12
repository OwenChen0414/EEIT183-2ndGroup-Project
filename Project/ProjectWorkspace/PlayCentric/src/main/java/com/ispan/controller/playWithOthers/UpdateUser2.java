package com.ispan.controller.playWithOthers;



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

@WebServlet("/UpdateUser2")
public class UpdateUser2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int count = 0;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
    	request.getParameterMap().forEach((key, value) -> extracted(count, key, value));
    	System.out.printf("取得%d筆資料%n",count);
    	
    	
        String id = request.getParameter("id");
        String nickname = request.getParameter("nickname");
        String gameId = request.getParameter("gameId");
        String pricingCategory = request.getParameter("pricingCategory");
        String amount = request.getParameter("amount");
        String onlineTime = request.getParameter("onlineTime");
        String offlineTime = request.getParameter("offlineTime");
        String transactionStatus = request.getParameter("transactionStatus");
        String description = request.getParameter("description");

        String sql = "UPDATE pwUser SET "
                + "nickname = ?, game_id = ?, pricing_category = ?, amount = ?, online_time = ?, offline_time = ?, transaction_status = ?, edited_time = GETDATE(), description = ? "
                + "WHERE id = ?";

        Connection conn = null;
        Context context = null;
        PreparedStatement stmt = null;
        try {
            context = new InitialContext();
            DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/db38");
            conn = ds.getConnection();

            System.out.println(sql);
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, nickname);
            stmt.setString(2, gameId);
            stmt.setString(3, pricingCategory);
            stmt.setString(4, amount);
            stmt.setString(5, onlineTime);
            stmt.setString(6, offlineTime);
            stmt.setString(7, transactionStatus);
            stmt.setString(8, description);
            stmt.setString(9, id);
            System.out.println("關了嗎"+stmt.isClosed());
            stmt.execute();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
                if (context != null) {
                    context.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        request.getRequestDispatcher("/dynamicView/playWithOthers/UpdateUser2.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

	private void extracted(int count, String key, String[] value) {
		this.count++;
		System.out.println("Key: " + key + ", Value: " + String.join(", ", value));
	}
}