package com.market.controller;
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
import com.market.bean.PropBean;
import com.market.dao.PropDao;
import com.market.util.JndiToJdbc;
@WebServlet("/CreateProp")
@MultipartConfig

public class CreateProp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
		request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");    
		int selectedGameId = Integer.valueOf(request.getParameter("gameId"));
        List<GameBean> games = (List<GameBean>) request.getSession().getAttribute("games");
        PropBean prop = new PropBean();
        //遊戲編號ID
        prop.setGameId(selectedGameId);
        String gameName =null; 
        //道具資訊
		prop.setPropName(request.getParameter("propName"));
		prop.setPropType(request.getParameter("propType"));
		prop.setPropRarity(request.getParameter("propRarity"));
		prop.setPropDescription(request.getParameter("propDescription"));
		//道具創建時間
		LocalDateTime now = LocalDateTime.now();
        Timestamp createTime = Timestamp.valueOf(now);
		prop.setCreatedTime(createTime);

		Part part =request.getPart("propImagePath");
		//上傳圖片
		String filename =part.getSubmittedFileName();
		response.getWriter().write(filename);
		String uploadDir = "D:\\ServletProject2Workspace\\Martket\\src\\main\\webapp\\images\\";
		part.write(uploadDir + filename);
        prop.setPropImagePath(filename);
        //呼叫DAO
		try {
			PropDao.createProp(selectedGameId, prop);
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		response.getWriter().write(uploadDir+selectedGameId);
		
		// 暫停2秒鐘 讓圖片上傳完成
		try {
		    Thread.sleep(2000); 
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
        request.setAttribute("gameId",prop.getGameId());	
		request.getRequestDispatcher("/GetSelectedProps").forward(request, response);
        
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
