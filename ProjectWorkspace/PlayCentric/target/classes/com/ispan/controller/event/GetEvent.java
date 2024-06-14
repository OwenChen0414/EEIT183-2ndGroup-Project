package com.ispan.controller.event;

import java.io.IOException;
import java.util.ArrayList;
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

@WebServlet("/GetEvent")
public class GetEvent extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String searchQuery = request.getParameter("searchQuery");
        String searchCriteria = request.getParameter("searchCriteria");
        List<EventBean> events = new ArrayList<>();
        String message = null;

        if (searchQuery == null || searchQuery.trim().isEmpty()) {
            forwardRequest(request, response, events, null);
            return;
        }

        try {
            Session session = HibernateSession.getFactory().getCurrentSession();
            EventDAOImlp eventDAOImlp = new EventDAOImlp(session);

            switch (searchCriteria) {
                case "eventno":
                    events.add(eventDAOImlp.getEvent(searchQuery));
                    break;
                case "name":
                    events = eventDAOImlp.getEventsByName(searchQuery);
                    break;
                case "description":
                    events = eventDAOImlp.getEventsByDescription(searchQuery);
                    break;
                case "date":
                    events = eventDAOImlp.getEventsByDate(searchQuery);
                    break;
                case "location":
                    events = eventDAOImlp.getEventsByLocation(searchQuery);
                    break;
                case "organizer":
                    events = eventDAOImlp.getEventsByOrganizer(searchQuery);
                    break;
            }

            if (events.isEmpty()) {
                message = "查無此活動資料";
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "查詢失敗：" + e.getMessage();
        }

        forwardRequest(request, response, events, message);
    }

    private void forwardRequest(HttpServletRequest request, HttpServletResponse response, List<EventBean> events, String message)
            throws ServletException, IOException {
        request.setAttribute("events", events);
        if (message != null) {
            request.setAttribute("message", message);
        }
        request.getRequestDispatcher("/dynamicView/event/GetEvent.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
