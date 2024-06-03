package com.market.dao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import jakarta.websocket.Session;

import java.io.IOException;
import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.naming.NamingException;

import com.market.bean.GameBean;
import com.market.util.JndiToJdbc;
@WebServlet("/DeleteProp")
@MultipartConfig

public class DeleteProp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        
        //遊戲編號ID
        String propId  =request.getParameter("propId");   
        response.getWriter().write(propId);
        
		//寫入sql
		JndiToJdbc jndiToJdbc = new JndiToJdbc();	
		try {
			Connection conn = jndiToJdbc.getConnection("db37");
			String sql = "DELETE FROM allProps WHERE propId = ?";
			PreparedStatement stmt =conn.prepareStatement(sql);
			stmt.setString(1, propId);
			stmt.executeUpdate();
			stmt.close();
			conn.close();
	        String gameId =request.getParameter("gameId");
	        request.setAttribute("gameId",gameId);	
			request.getRequestDispatcher("/GetAllProps").forward(request, response);

		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}     
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
