package com.ispan.controller.games;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import com.ispan.bean.buy.Discount;
import com.ispan.bean.games.Game;
import com.ispan.bean.games.GameCategoryLib;
import com.ispan.dao.buy.DiscountDAO;
import com.ispan.dao.games.GamesDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/GetUpdateGame")
public class GetUpdateGame extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
		GamesDAO gamesDAO = new GamesDAO();
		List<String> allCategory = gamesDAO.getAllCategory();
		int gameId = Integer.parseInt(request.getParameter("id"));
		Game game = gamesDAO.getOne(gameId);
		List<GameCategoryLib> getCategoryNames = gamesDAO.getCategorys(gameId);
		List<String> categoryNames = new ArrayList<String>();
		for (GameCategoryLib gameCategoryLib : getCategoryNames) {
			categoryNames.add(gameCategoryLib.getGameCategoryName());
		}
		game.setCategoryNames(categoryNames);
		DiscountDAO discountDAO = new DiscountDAO();
		Discount discount = discountDAO.getByGame(gameId);
		request.setAttribute("discount", discount);
		request.setAttribute("game", game);
		request.setAttribute("categorys", allCategory);
		request.getRequestDispatcher("/dynamicView/games/get-update-game.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		 throws ServletException, IOException {
		 doGet(request, response);
	}
}
