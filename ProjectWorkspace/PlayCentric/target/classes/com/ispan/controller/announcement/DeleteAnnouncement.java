package com.ispan.controller.announcement;

import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ispan.bean.announcement.Announcement;
import com.ispan.bean.announcement.AnnouncementCategory;
import com.ispan.dao.announcement.AnnouncementCategoryDAO;
import com.ispan.dao.announcement.AnnouncementDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DeleteAnnouncement")
public class DeleteAnnouncement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
		AnnouncementDAO announcementDAO = new AnnouncementDAO();
		announcementDAO.delete(Integer.parseInt(request.getParameter("id")));
		List<Announcement> announcements = announcementDAO.getAll();
		AnnouncementCategoryDAO categoryDAO = new AnnouncementCategoryDAO();
		List<AnnouncementCategory> categorys = categoryDAO.getAll();
		Map<Integer, String> categoryMap = new HashMap<Integer, String>();
		for (AnnouncementCategory category : categorys) {
			categoryMap.put(category.getCategoryId(), category.getCategoryName());
		}
		request.setAttribute("categoryMap", categoryMap);
		request.setAttribute("announcements", announcements);
		request.getRequestDispatcher("/dynamicView/announcement/back-announcement.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		 throws ServletException, IOException {
		 doGet(request, response);
	}
}
