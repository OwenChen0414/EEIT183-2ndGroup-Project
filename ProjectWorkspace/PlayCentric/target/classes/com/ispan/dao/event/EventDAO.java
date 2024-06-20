package com.ispan.dao.event;

import java.util.List;

import com.ispan.bean.event.EventBean;


public interface EventDAO {
    int addEvent(EventBean event) throws Exception;
    int deleteEvent(String eventNo) throws Exception;
    EventBean getEvent(String eventNo) throws Exception;
    List<EventBean> getEventsByName(String name) throws Exception;
    List<EventBean> getEventsByDescription(String description) throws Exception;
    List<EventBean> getEventsByDate(String date) throws Exception;
    List<EventBean> getEventsByLocation(String location) throws Exception;
    List<EventBean> getEventsByOrganizer(String organizer) throws Exception;
    List<EventBean> getAllEvents() throws Exception;
    int updateEvent(EventBean event) throws Exception;
}
