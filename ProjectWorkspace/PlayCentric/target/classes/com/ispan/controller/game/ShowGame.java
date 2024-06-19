package com.ispan.controller.game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ispan.bean.announcement.Announcement;
import com.ispan.bean.announcement.AnnouncementCategory;
import com.ispan.bean.games.Game;
import com.ispan.bean.games.GameCategoryLib;
import com.ispan.dao.announcement.AnnouncementCategoryDAO;
import com.ispan.dao.announcement.AnnouncementDAO;
import com.ispan.dao.games.GamesDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ShowGame")
public class ShowGame extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
		GamesDAO gamesDAO = new GamesDAO();
		int gameId = Integer.parseInt(request.getParameter("id"));
		Game game = gamesDAO.getOne(gameId);
		List<String> categorys = new ArrayList<String>();
		List<GameCategoryLib> getCategorys = gamesDAO.getCategorys(gameId);
		for (GameCategoryLib gameCategoryLib : getCategorys) {
			categorys.add(gameCategoryLib.getGameCategoryName());
		}
		game.setCategoryNames(categorys);
		request.setAttribute("game", game);
		request.getRequestDispatcher("/dynamicView/games/show-game.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		 throws ServletException, IOException {
		 doGet(request, response);
	}
}
