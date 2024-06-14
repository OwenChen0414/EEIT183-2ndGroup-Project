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

@WebServlet("/UpdateEvent")
public class UpdateEvent extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String eventno = request.getParameter("eventno");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String date = request.getParameter("date");
        String location = request.getParameter("location");
        String organizer = request.getParameter("organizer");

        String message = "";

        if (eventno == null || (name == null && description == null && date == null && location == null && organizer == null)) {
            request.getRequestDispatcher("/dynamicView/event/UpdateEvent.jsp").forward(request, response);
            return;
        }

        EventBean eventBean = new EventBean();
        eventBean.setEventno(eventno);
        eventBean.setName(name);
        eventBean.setDescription(description);
        eventBean.setDate(request.getParameter("date"));
        eventBean.setLocation(location);
        eventBean.setOrganizer(organizer);

        int rowsAffected = 0;
        try {
            Session session = HibernateSession.getFactory().getCurrentSession();
            EventDAOImlp eventDAOImlp = new EventDAOImlp(session);
            rowsAffected = eventDAOImlp.updateEvent(eventBean);
        } catch (Exception e) {
            e.printStackTrace();
            message = "修改失敗：" + e.getMessage();
        }

        message = rowsAffected > 0 ? "修改成功" : "修改失敗";
        request.setAttribute("message", message);
        request.getRequestDispatcher("/GetAllEvent").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
