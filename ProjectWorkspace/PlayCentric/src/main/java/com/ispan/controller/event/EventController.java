package com.ispan.controller.event;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ispan.bean.event.EventBean;
import com.ispan.dao.event.EventDAOImlp;

@Controller
public class EventController {

    @Autowired
    private EventDAOImlp eventDAO;

    
    @GetMapping("/event/add")
    public String showAddEventForm() {
        return "/event/addEvent";
    }
    
    @GetMapping("/event/update")
    public String showUpdateEventForm() {
    	return "/event/updateEvent";
    }
    
    @GetMapping("/event/delete")
    public String showDeleteEventForm() {
    	return "/event/deleteEvent";
    }
    
    @GetMapping("/event/getOne")
    public String showGetoneEventForm() {
    	return "/event/getEvent";
    }

    @PostMapping("/event/addEvent")
    public String addEvent(@RequestParam("name") String name,
                           @RequestParam("description") String description,
                           @RequestParam("date") String date,
                           @RequestParam("location") String location,
                           @RequestParam("organizer") String organizer,
                           Model model) {
        EventBean event = new EventBean();
        event.setName(name);
        event.setDescription(description);
        event.setDate(date);
        event.setLocation(location);
        event.setOrganizer(organizer);

        try {
            eventDAO.addEvent(event);
            model.addAttribute("message", "新增成功!");
        } catch (Exception e) {
            model.addAttribute("message", "新增失敗!" + e.getMessage());
        }
        return "redirect:/event/getAllEvent";
    }

    @GetMapping("/event/getAllEvent")
    public String getAllEvents(Model model) {
        try {
            List<EventBean> events = eventDAO.getAllEvents();
            model.addAttribute("events", events);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/event/getAllEvent";
    }

    @GetMapping("/event/deleteEvent")
    public String deleteEvent(@RequestParam("eventno") String eventno, Model model) {
        try {
            eventDAO.deleteEvent(eventno);
            model.addAttribute("message", "刪除成功!");
        } catch (Exception e) {
            model.addAttribute("message", "刪除失敗!" + e.getMessage());
        }
        return "redirect:/event/getAllEvent";
    }

    @GetMapping("/event/getEvent")
    public String searchEvent(@RequestParam("query") String query,
                              @RequestParam("criteria") String criteria, Model model) {
        try {
            List<EventBean> events;
            switch (criteria) {
                case "eventno":
                    events = List.of(eventDAO.getEvent(query));
                    break;
                case "name":
                    events = eventDAO.getEventsByName(query);
                    break;
                case "description":
                    events = eventDAO.getEventsByDescription(query);
                    break;
                case "date":
                    events = eventDAO.getEventsByDate(query);
                    break;
                case "location":
                    events = eventDAO.getEventsByLocation(query);
                    break;
                case "organizer":
                    events = eventDAO.getEventsByOrganizer(query);
                    break;
                default:
                    events = List.of();
            }
            model.addAttribute("events", events);
            model.addAttribute("query", query);
        } catch (Exception e) {
            model.addAttribute("message", "查詢失敗：" + e.getMessage());
        }
        return "/event/getEvent";
    }

    @GetMapping("/event/updateEvent")
    public String showUpdateEventForm(@RequestParam("eventno") String eventno, Model model) {
        try {
            EventBean event = eventDAO.getEvent(eventno);
            model.addAttribute("event", event);
        } catch (Exception e) {
            model.addAttribute("message", "查無此活動：" + e.getMessage());
        }
        return "/event/updateEvent";
    }
    
    
    @GetMapping("/event/update{id}")
    public String updateById(@PathVariable("id") String eventno,Model model ) {
    	EventBean event = eventDAO.getEvent(eventno);
    	model.addAttribute("event",event);
    	return "/event/updateEvent";
    }
    
    
    @PostMapping("/event/updateEvent")
    public String updateEvent(@RequestParam("eventno") String eventno,
                              @RequestParam("name") String name,
                              @RequestParam("description") String description,
                              @RequestParam("date") String date,
                              @RequestParam("location") String location,
                              @RequestParam("organizer") String organizer,
                              Model model) {
        EventBean event = new EventBean();
        event.setEventno(eventno);
        event.setName(name);
        event.setDescription(description);
        event.setDate(date);
        event.setLocation(location);
        event.setOrganizer(organizer);

        try {
            eventDAO.updateEvent(event);
            model.addAttribute("message", "修改成功");
        } catch (Exception e) {
            model.addAttribute("message", "修改失敗：" + e.getMessage());
        }
        return "redirect:/event/getAllEvent";
    }
}