package com.ispan.controller.games;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.ispan.bean.games.Game;
import com.ispan.dao.buy.DiscountDAO;
import com.ispan.dao.games.GamePhotosDAO;
import com.ispan.dao.games.GamesDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DeleteGame")
public class DeleteGame extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
		GamesDAO gamesDAO = new GamesDAO();
		GamePhotosDAO photosDAO = new GamePhotosDAO();
		DiscountDAO discountDAO = new DiscountDAO();
		int gameId = Integer.parseInt(request.getParameter("id"));
		Game game = gamesDAO.getOne(gameId);
		String filePath = "D:\\Servlet\\workspace\\Project2\\src\\main\\webapp\\" + game.getPhotoPath();
		File file = new File(filePath);
		file.delete();
		photosDAO.deletePhotoLib(game.getPhotoPath());
		photosDAO.deletePhotos(gameId);
		gamesDAO.delete(gameId);
		
		List<Game> games = gamesDAO.getAll();
		request.setAttribute("games", games);
		request.getRequestDispatcher("/dynamicView/games/back-games.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		 throws ServletException, IOException {
		 doGet(request, response);
	}
}
