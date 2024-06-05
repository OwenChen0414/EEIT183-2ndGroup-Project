package com.ispan.controller.market;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ispan.bean.market.GameBean;
@WebServlet("/GetAllGames")

public class GetAllGames extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GameBean yuGiOhDuelLinks = new GameBean(1,"遊戲王");
		GameBean cs2 = new GameBean(2,"CS2");
		List<GameBean> games = new ArrayList<>();
		games.addAll(Arrays.asList(yuGiOhDuelLinks, cs2));
		HttpSession session = request.getSession();
		session.setAttribute("games", games);
		request.getRequestDispatcher("/dynamicView/market/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
