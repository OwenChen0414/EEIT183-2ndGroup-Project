package com.market.dao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import javax.naming.NamingException;
import com.market.bean.PropBean;
import com.market.util.JndiToJdbc;
@WebServlet("/GetPropData")
@MultipartConfig

public class GetPropData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
		String propId =request.getParameter("propId");
        String propName =request.getParameter("propName");
		String propType =request.getParameter("propType");
		String propRarity =request.getParameter("propRarity");
		String propDescription =request.getParameter("propDescription");
		String propImagePath =request.getParameter("propImagePath");
		String gameId =request.getParameter("gameId");
//      把值送給jsp顯示
		request.setAttribute("propId", propId);
		request.setAttribute("propName", propName);
		request.setAttribute("propType", propType);
		request.setAttribute("propRarity", propRarity);
		request.setAttribute("propDescription", propDescription);
		request.setAttribute("propImagePath", propImagePath);
		request.setAttribute("gameId", gameId);
		request.getRequestDispatcher("/jsp/UpdateProp.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
