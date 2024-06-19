package com.ispan.controller.game;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.ispan.bean.buy.Discount;
import com.ispan.bean.games.Game;
import com.ispan.bean.games.GameCategorys;
import com.ispan.dao.buy.DiscountDAO;
import com.ispan.dao.games.GamesDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/UpdateGame")
public class UpdateGame extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
		GamesDAO gamesDAO = new GamesDAO();
		int gameId = Integer.parseInt(request.getParameter("id"));
		Game game = gamesDAO.getOne(gameId);
		game.setGameName(request.getParameter("gameName"));
		game.setIntroduction(request.getParameter("introduction"));
		game.setDescription(request.getParameter("description"));
		game.setDeveloper(request.getParameter("developer"));
		game.setPublisher(request.getParameter("publisher"));
		game.setPrice(Integer.parseInt(request.getParameter("price")));
		gamesDAO.updateInfo(gameId, game);
		gamesDAO.deleteCategory(gameId);
		String[] getCategoryNames = request.getParameterValues("categoryNames");
		List<String> categoryNames = new ArrayList<String>();
		for (String category : getCategoryNames) {
			categoryNames.add(category);
		}
		game.setCategoryNames(categoryNames);
		GameCategorys gameCategorys = new GameCategorys();
		for (String category : categoryNames) {
			int categoryId = gamesDAO.getCategoryId(category);
			gameCategorys.setCategoryId(categoryId);
			gameCategorys.setGameId(gameId);
			gamesDAO.insertCategory(gameCategorys);
		}
		DiscountDAO discountDAO = new DiscountDAO();
		Discount discount = discountDAO.getByGame(gameId);
		if (discount.getDiscountId() != 0) {
			Discount newDiscount = new Discount();
			String startAt = request.getParameter("discountStart").replace("/", "-");
			String EndAt = request.getParameter("discountEnd").replace("/", "-");
			newDiscount.setStartAt(Date.valueOf(startAt));
			newDiscount.setEndAt(Date.valueOf(EndAt));
			newDiscount.setGameId(gameId);
			newDiscount.setDiscountRate(Double.valueOf(request.getParameter("discountRate")));
			discountDAO.update(newDiscount);
		}
		else {
			Discount newDiscount = new Discount();
			String startAt = request.getParameter("discountStart").replace("/", "-");
			String EndAt = request.getParameter("discountEnd").replace("/", "-");
			newDiscount.setStartAt(Date.valueOf(startAt));
			newDiscount.setEndAt(Date.valueOf(EndAt));
			newDiscount.setGameId(gameId);
			newDiscount.setDiscountRate(Double.valueOf(request.getParameter("discountRate")));
			discountDAO.insert(newDiscount);
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
