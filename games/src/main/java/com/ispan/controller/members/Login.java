package com.ispan.controller.members;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.ispan.bean.announcement.Announcement;
import com.ispan.bean.announcement.AnnouncementCategory;
import com.ispan.bean.buy.GameWithCart;
import com.ispan.bean.games.Game;
import com.ispan.bean.games.GameCategoryLib;
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

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        MembersDAO membersDAO = new MembersDAO();
        Members member = membersDAO.loginCheck(account, password);
        if (member.getMemId() != 0) {
        	HttpSession session = request.getSession();
        	session.setAttribute("loginMember", member);
        	int memId = member.getMemId();
			BuyGameDAO buyGameDAO = new BuyGameDAO();
			List<GameWithCart> cart = buyGameDAO.getCart(memId);
			request.setAttribute("inCart", cart);
    		GamesDAO gamesDAO = new GamesDAO();
    		List<Game> games = gamesDAO.getAllOn();
    		for (Game game : games) {
    			List<GameCategoryLib> getCategorys = gamesDAO.getCategorys(game.getGameId());
    			List<String> categorys = new ArrayList<String>();
    			for (GameCategoryLib gameCategoryLib : getCategorys) {
    				String categoryName = gameCategoryLib.getGameCategoryName();
    				categorys.add(categoryName);
    			}
    			game.setCategoryNames(categorys);
    		}
    		List<Game> newGames = new ArrayList<Game>();
    		if (request.getParameter("categoryId") != null) {
    			int categoryId = Integer.parseInt(request.getParameter("categoryId"));
    			for (Game game : games) {
    				List<String> categoryNames = game.getCategoryNames();
    				for (String string : categoryNames) {
    					if (string.equals(gamesDAO.getCategoryName(categoryId))) {
    						newGames.add(game);
    						break;
    					}
    				}
    			}
    			request.setAttribute("games", newGames);
    		}else {
    			request.setAttribute("games", games);
    		}
    		List<String> allCategory = gamesDAO.getAllCategory();
    		request.setAttribute("allCategory", allCategory);
    		request.getRequestDispatcher("/dynamicView/games/game-store.jsp").forward(request, response);
		}
        else {
        	request.getRequestDispatcher("/view/members/login-failed.html").forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		 throws ServletException, IOException {
		 doGet(request, response);
	}
}
