package com.ispan.controller.event;

import java.io.IOException;
import java.util.List;

import org.hibernate.Session;

import com.ispan.bean.event.EventBean;
import com.ispan.dao.event.EventDAOImlp;
import com.ispan.util.member.HibernateSession;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/GetAllEvent")
public class GetAllEvent extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<EventBean> events = null;
        try {
            Session session = HibernateSession.getFactory().getCurrentSession();
            EventDAOImlp eventDAOImlp = new EventDAOImlp(session);
            events = eventDAOImlp.getAllEvents();
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("events", events);
        request.getRequestDispatcher("/dynamicView/event/GetAllEvent.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
