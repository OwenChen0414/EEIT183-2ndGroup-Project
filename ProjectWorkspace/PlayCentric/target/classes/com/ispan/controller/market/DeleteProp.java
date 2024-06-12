package com.ispan.controller.market;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.ispan.dao.market.PropDao;
import com.ispan.util.market.JndiToJdbc;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/DeleteProp")
@MultipartConfig

public class DeleteProp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        
        //遊戲編號ID
        int propId  =Integer.valueOf(request.getParameter("propId")) ;   
        
        response.getWriter().write(propId);
        
		//寫入sql
		JndiToJdbc jndiToJdbc = new JndiToJdbc();	
		try {
			Connection conn = jndiToJdbc.getConnection("db37");
			PropDao.deleteProp(propId);
	        String gameId =request.getParameter("gameId");
	        request.setAttribute("gameId",gameId);	
			request.getRequestDispatcher("/GetSelectedProps").forward(request, response);

		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}     
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
