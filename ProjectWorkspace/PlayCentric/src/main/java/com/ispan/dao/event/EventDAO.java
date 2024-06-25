package com.ispan.dao.event;

import java.util.List;

import com.ispan.bean.event.EventBean;

public interface EventDAO {
    int addEvent(EventBean event);
    int deleteEvent(String eventNo);
    EventBean getEvent(String eventNo);
    List<EventBean> getEventsByName(String name);
    List<EventBean> getEventsByDescription(String description);
    List<EventBean> getEventsByDate(String date);
    List<EventBean> getEventsByLocation(String location);
    List<EventBean> getEventsByOrganizer(String organizer);
    List<EventBean> getAllEvents();
    int updateEvent(EventBean event);
}