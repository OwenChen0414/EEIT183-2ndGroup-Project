package com.ispan.controller.games;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ispan.bean.announcement.Announcement;
import com.ispan.bean.announcement.AnnouncementCategory;
import com.ispan.bean.games.Game;
import com.ispan.bean.games.GameCategorys;
import com.ispan.bean.games.GamePhotoLib;
import com.ispan.bean.games.GamePhotos;
import com.ispan.dao.announcement.AnnouncementCategoryDAO;
import com.ispan.dao.announcement.AnnouncementDAO;
import com.ispan.dao.games.GamePhotosDAO;
import com.ispan.dao.games.GamesDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@MultipartConfig(location="D:/images/games/")
@WebServlet("/InsertGame")
public class InsertGame extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
		GamesDAO gamesDAO = new GamesDAO();
		GamePhotosDAO photosDAO = new GamePhotosDAO();
		Game game = new Game();
		game.setGameName(request.getParameter("gameName"));
		game.setPrice(Integer.parseInt(request.getParameter("price")));
		game.setDescription(request.getParameter("description"));
		game.setDeveloper(request.getParameter("developer"));
		game.setPublisher(request.getParameter("publisher"));
		game.setIntroduction(request.getParameter("introduction"));
		gamesDAO.insert(game);
		Game theGame = gamesDAO.getWithName(request.getParameter("gameName"));
		List<String> category = new ArrayList<String>();
		String[] categorys = request.getParameterValues("categoryName");
		for (String string : categorys) {
			int categoryId = gamesDAO.getCategoryId(string);
			int gameId = theGame.getGameId();
			GameCategorys gameCategorys = new GameCategorys();
			gameCategorys.setCategoryId(categoryId);
			gameCategorys.setGameId(gameId);
			gamesDAO.insertCategory(gameCategorys);
		}
		theGame.setCategoryNames(category);
		
		Part part = request.getPart("photo");
		String fileName = "images_";
		fileName += theGame.getGameId() + ".png";
		GamePhotoLib photoLib = new GamePhotoLib();
		photoLib.setPhotoPath("images/games/"+fileName);
		photosDAO.insertLib(photoLib);
		GamePhotos photos = new GamePhotos();
		photos.setGameId(theGame.getGameId());
		photos.setPhotoId(photosDAO.getOneId("images/games/" +fileName));
		photosDAO.insert(photos);
		part.write(fileName);
		
		List<Game> games = gamesDAO.getAll();
		request.setAttribute("games", games);
		request.getRequestDispatcher("/dynamicView/games/back-games.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		 throws ServletException, IOException {
		 doGet(request, response);
	}
}
