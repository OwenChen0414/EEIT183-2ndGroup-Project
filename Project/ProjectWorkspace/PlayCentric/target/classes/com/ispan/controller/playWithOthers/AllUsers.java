package com.ispan.controller.playWithOthers;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.ispan.bean.playWithOthers.PlayUserBean;
import com.ispan.dao.playWithOthers.PwDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AllUsers")
public class AllUsers extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 創建一個PwDAO實例
        PwDAO pwDAO = new PwDAO();

        try {
            // 在PwDAO實例上調用getAllUsers方法
            List<PlayUserBean> users = pwDAO.getAllUsers();
            request.setAttribute("users", users);
            request.getRequestDispatcher("/dynamicView/playWithOthers/SelectAll.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}