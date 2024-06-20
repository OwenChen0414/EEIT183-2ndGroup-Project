package com.ispan.controller.market;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.RequestDispatcher;

import java.io.IOException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.ispan.bean.market.Game2;
import com.ispan.dao.market.Game2Service;
import com.ispan.util.member.HibernateSession;

@WebServlet("/DemoGameServletAction")
public class DemoGameServletAction extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processAction(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processAction(request, response);
    }

    private void processAction(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        SessionFactory factory = HibernateSession.getFactory();
        Session session = factory.getCurrentSession();
        Game2Service gService = new Game2Service(session);

        List<Game2> games = gService.findAll();

        // 設置 HttpSession 屬性
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("games", games);
        

        // 使用轉發 forward 到 index.jsp 頁面
        RequestDispatcher dispatcher = request.getRequestDispatcher("/dynamicView/market/propSheet.jsp");
        dispatcher.forward(request, response);
    }
}
