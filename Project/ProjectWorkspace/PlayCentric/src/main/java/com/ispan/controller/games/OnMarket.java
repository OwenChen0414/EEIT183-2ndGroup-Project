package com.ispan.controller.games;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.ispan.bean.announcement.Announcement;
import com.ispan.bean.announcement.AnnouncementCategory;
import com.ispan.bean.games.Game;
import com.ispan.dao.announcement.AnnouncementCategoryDAO;
import com.ispan.dao.announcement.AnnouncementDAO;
import com.ispan.dao.games.GamesDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/OnMarket")
public class OnMarket extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
		GamesDAO gamesDAO = new GamesDAO();
		int id = Integer.parseInt(request.getParameter("id"));
		Game game = gamesDAO.getOne(id);
		if (game.getOnMarketTime() != null) {
			gamesDAO.updateRemove(id);
		}
		else if (game.getOnMarketTime() == null) {
			gamesDAO.updateOnMarket(id);
		}
		List<Game> games = gamesDAO.getAll();
		request.setAttribute("games", games);
		request.getRequestDispatcher("/dynamicView/games/back-games.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		 throws ServletException, IOException {
		 doGet(request, response);
	}
}
