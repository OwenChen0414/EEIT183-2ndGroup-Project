package com.ispan.controller.buy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.ispan.bean.announcement.Announcement;
import com.ispan.bean.announcement.AnnouncementCategory;
import com.ispan.bean.buy.GameCart;
import com.ispan.bean.buy.GameWithCart;
import com.ispan.bean.games.Game;
import com.ispan.bean.members.Members;
import com.ispan.dao.announcement.AnnouncementCategoryDAO;
import com.ispan.dao.announcement.AnnouncementDAO;
import com.ispan.dao.buy.BuyGameDAO;
import com.ispan.dao.games.GamesDAO;
import com.ispan.dao.members.MembersDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ShowCart")
public class ShowCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
        HttpSession session = request.getSession();
        Members member = (Members) session.getAttribute("loginMember");
        List<GameWithCart> cart = new ArrayList<GameWithCart>();
        String memName = null;
        if (member != null) {
			int memId = member.getMemId();
			BuyGameDAO buyGameDAO = new BuyGameDAO();
			MembersDAO membersDAO = new MembersDAO();
			cart = buyGameDAO.getCart(memId);
			memName = membersDAO.getName(memId);
		}
        
        request.setAttribute("memName", memName);
		request.setAttribute("games", cart);
		request.getRequestDispatcher("/dynamicView/buy/show-cart.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		 throws ServletException, IOException {
		 doGet(request, response);
	}
}
