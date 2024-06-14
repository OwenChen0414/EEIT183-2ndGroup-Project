package com.ispan.controller.announcement;

import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import com.ispan.bean.announcement.Announcement;
import com.ispan.bean.announcement.AnnouncementCategory;
import com.ispan.dao.announcement.AnnouncementCategoryDAO;
import com.ispan.dao.announcement.AnnouncementDAO;
import com.ispan.util.member.HibernateSession;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/InsertAnnouncement")
public class InsertAnnouncement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		
		
		
		
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
        
        
        
        
        Session session = HibernateSession.getFactory().getCurrentSession();
		AnnouncementDAO announcementDAO = new AnnouncementDAO(session);
		
		Announcement Announcement = new Announcement();
		
		
		
		Announcement.setContent(request.getParameter("content"));
		Announcement.setTitle(request.getParameter("title"));
		
		
		
		
		
		Announcement.setCategoryId(Integer.parseInt(request.getParameter("categoryId")));
		AnnouncementCategoryDAO categoryDAO = new AnnouncementCategoryDAO(session);
		AnnouncementCategory category = categoryDAO.getOne(Integer.parseInt(request.getParameter("categoryId")));
		Announcement.setAnnouncementCategory(category);
		announcementDAO.insert(Announcement);
		List<Announcement> announcements = announcementDAO.getAll();
		request.setAttribute("announcements", announcements);
		request.getRequestDispatcher("/dynamicView/announcement/back-announcement.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		 throws ServletException, IOException {
		 doGet(request, response);
	}
}
