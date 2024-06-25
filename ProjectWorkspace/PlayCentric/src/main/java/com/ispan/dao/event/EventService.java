package com.ispan.dao.event;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ispan.bean.event.EventBean;

@Service
@Transactional
public class EventService {

    @Autowired
    private EventDAOImlp eventDAO;

    public int addEvent(EventBean event) throws Exception {
        return eventDAO.addEvent(event);
    }

    public int deleteEvent(String eventNo) throws Exception {
        return eventDAO.deleteEvent(eventNo);
    }

    public EventBean getEvent(String eventNo) throws Exception {
        return eventDAO.getEvent(eventNo);
    }

    public List<EventBean> getEventsByName(String name) throws Exception {
        return eventDAO.getEventsByName(name);
    }

    public List<EventBean> getEventsByDescription(String description) throws Exception {
        return eventDAO.getEventsByDescription(description);
    }

    public List<EventBean> getEventsByDate(String date) throws Exception {
        return eventDAO.getEventsByDate(date);
    }

    public List<EventBean> getEventsByLocation(String location) throws Exception {
        return eventDAO.getEventsByLocation(location);
    }

    public List<EventBean> getEventsByOrganizer(String organizer) throws Exception {
        return eventDAO.getEventsByOrganizer(organizer);
    }

    public List<EventBean> getAllEvents() throws Exception {
        return eventDAO.getAllEvents();
    }

    public int updateEvent(EventBean event) throws Exception {
        return eventDAO.updateEvent(event);
    }
}