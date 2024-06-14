package com.ispan.controller.event;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.hibernate.Session;

import com.ispan.bean.event.EventBean;
import com.ispan.dao.event.EventDAOImlp;
import com.ispan.util.member.HibernateSession;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AddEvent")
public class AddEvent extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String eventno = request.getParameter("eventno");
        if (eventno == null) {
            request.getRequestDispatcher("/dynamicView/event/AddEvent.jsp").forward(request, response);
            return;
        }

        // 初始化 EventBean
        EventBean event = new EventBean();
        event.setEventno(eventno);
        event.setName(request.getParameter("name"));
        event.setDescription(request.getParameter("description"));
        event.setDate(request.getParameter("date"));
        event.setLocation(request.getParameter("location"));
        event.setOrganizer(request.getParameter("organizer"));

        int count = 0;
        try {
            Session session = HibernateSession.getFactory().getCurrentSession();
            EventDAOImlp eventDAOImlp = new EventDAOImlp(session);
            count = eventDAOImlp.addEvent(event);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String message = count > 0 ? "新增成功!" : "新增失敗!";
        request.setAttribute("message", message);
        request.getRequestDispatcher("/GetAllEvent").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
