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
@WebServlet("/UpdateProp")
@MultipartConfig

public class UpdateProp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        //道具資訊
        String propId  =request.getParameter("propId");
		String propName =request.getParameter("propName");
		String propType =request.getParameter("propType");
		String propRarity =request.getParameter("propRarity");
		String propDescription =request.getParameter("propDescription");	
//		"待完成"defaultPropImagePath為update前的圖片名稱，若有update圖片則使用filename(新)，否則update圖片則使用defaultPropImagePath(舊)	
//		String defaultPropImagePath =request.getParameter("defaultPropImagePath");	
		
		Part part =request.getPart("propImagePath");
		String filename =part.getSubmittedFileName();
		String uploadDir = "D:\\testWorkspace\\Martket\\src\\main\\webapp\\images\\";
		part.write(uploadDir + filename);

        
//      檢查是否有讀取到資料
//		response.getWriter().write(propName);
//		response.getWriter().write(propType);
//		response.getWriter().write(propRarity);
//		response.getWriter().write(propDescription);
//		response.getWriter().write(filename);
//		response.getWriter().write(propId);

		
		  
//		寫入sql
		JndiToJdbc jndiToJdbc = new JndiToJdbc();	
		try {
			Connection conn = jndiToJdbc.getConnection("db37");
			String sql = "UPDATE allProps SET propName=?, propType=?, propRarity=?, propDescription=?, propImagePath=?, updatedTime=GETDATE() WHERE propId=?";	
			PreparedStatement stmt =conn.prepareStatement(sql);
			stmt.setString(1, propName);
			stmt.setString(2, propType);
			stmt.setString(3, propRarity);
			stmt.setString(4, propDescription);
			stmt.setString(5, filename);
			stmt.setString(6, propId);
			stmt.executeUpdate();
			stmt.close();
			conn.close();
			
//			 暫停1秒鐘 讓圖片上傳完成
			try {
			    Thread.sleep(2000); 
			} catch (InterruptedException e) {
			    e.printStackTrace();
			}		
	        String gameId =request.getParameter("gameId");
			response.getWriter().write(gameId);
	        request.setAttribute("gameId",gameId);
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}        
		request.getRequestDispatcher("/GetAllProps").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
