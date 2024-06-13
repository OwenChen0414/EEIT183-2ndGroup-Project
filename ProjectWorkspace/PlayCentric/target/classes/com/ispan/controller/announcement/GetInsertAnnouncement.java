package com.ispan.controller.announcement;

import java.io.IOException;
import java.util.List;

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

@WebServlet("/GetInsertAnnouncement")
public class GetInsertAnnouncement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
        Session session = HibernateSession.getFactory().getCurrentSession();
		AnnouncementCategoryDAO announcementCategoryDAO = new AnnouncementCategoryDAO(session);
		List<AnnouncementCategory> categorys = announcementCategoryDAO.getAll();
		request.setAttribute("categorys", categorys);
		request.getRequestDispatcher("/dynamicView/announcement/get-insert-announcement.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		 throws ServletException, IOException {
		 doGet(request, response);
	}
}
