package com.ispan.controller.event;

import java.io.IOException;

import org.hibernate.Session;

import com.ispan.dao.event.EventDAOImlp;
import com.ispan.util.member.HibernateSession;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DeleteEvent")
public class DeleteEvent extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String eventno = request.getParameter("eventno");
        if (eventno == null) {
            request.getRequestDispatcher("/dynamicView/event/DeleteEvent.jsp").forward(request, response);
            return;
        }

        int count = 0;
        try {
            Session session = HibernateSession.getFactory().getCurrentSession();
            EventDAOImlp eventDAOImlp = new EventDAOImlp(session);
            count = eventDAOImlp.deleteEvent(eventno);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String message = count > 0 ? "刪除成功!" : "刪除失敗!";
        request.setAttribute("message", message);
        request.getRequestDispatcher("/GetAllEvent").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
