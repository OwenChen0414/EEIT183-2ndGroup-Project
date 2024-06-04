package com.ispan.controller.announcement;

import java.io.IOException;
import java.util.List;

import com.ispan.bean.announcement.Announcement;
import com.ispan.bean.announcement.AnnouncementCategory;
import com.ispan.dao.announcement.AnnouncementCategoryDAO;
import com.ispan.dao.announcement.AnnouncementDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/GetUpdateAnnouncement")
public class GetUpdateAnnouncement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
		AnnouncementDAO announcementDAO = new AnnouncementDAO();
		Announcement announcement = announcementDAO.getOne(Integer.parseInt(request.getParameter("id")));
		request.setAttribute("announcement", announcement);
		AnnouncementCategoryDAO announcementCategoryDAO = new AnnouncementCategoryDAO();
		List<AnnouncementCategory> categorys = announcementCategoryDAO.getAll();
		request.setAttribute("categorys", categorys);
		request.getRequestDispatcher("/dynamicView/announcement/get-update-announcement.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		 throws ServletException, IOException {
		 doGet(request, response);
	}
}
