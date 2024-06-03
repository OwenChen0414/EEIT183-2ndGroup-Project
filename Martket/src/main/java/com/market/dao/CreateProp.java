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
@WebServlet("/CreateProp")
@MultipartConfig

public class CreateProp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        
        //遊戲編號ID
        int selectedGameId = Integer.valueOf( request.getParameter("game"));
        List<GameBean> games = (List<GameBean>) request.getSession().getAttribute("games");
        String gameName =null;
        

        //道具資訊
		String propName =request.getParameter("propName");
		String propType =request.getParameter("propType");
		String propRarity =request.getParameter("propRarity");
		String propDescription =request.getParameter("propDescription");	
        //道具創建時間
		LocalDateTime now = LocalDateTime.now();
        Timestamp createTime = Timestamp.valueOf(now);
		
		
		Part part =request.getPart("propImagePath");
		//上傳圖片
		String filename =part.getSubmittedFileName();
		response.getWriter().write(filename);
		String uploadDir = "D:\\testWorkspace\\Martket\\src\\main\\webapp\\images\\";
		response.getWriter().write(uploadDir+selectedGameId);
		part.write(uploadDir + filename);
        

		//寫入sql
		JndiToJdbc jndiToJdbc = new JndiToJdbc();	
		try {
			Connection conn = jndiToJdbc.getConnection("db37");
			String sql = "INSERT INTO allProps (gameId,propName,propType,propRarity,propDescription,propImagePath,createdTime)"
					+ "VALUES (?,?,?,?,?,?,GETDATE())";
			PreparedStatement stmt =conn.prepareStatement(sql);
			stmt.setInt(1, selectedGameId);
			stmt.setString(2, propName);
			stmt.setString(3, propType);
			stmt.setString(4, propRarity);
			stmt.setString(5, propDescription);
			stmt.setString(6, filename);
			stmt.executeUpdate();
			stmt.close();
			conn.close();
			
			// 暫停1秒鐘 讓圖片上傳完成
			try {
			    Thread.sleep(2000); 
			} catch (InterruptedException e) {
			    e.printStackTrace();
			}
			

		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/GetAllProps").forward(request, response);
        
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
