package com.ispan.controller.market;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.time.Instant;
import java.time.LocalDateTime;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.ispan.bean.market.Game2;
import com.ispan.bean.market.Prop;
import com.ispan.dao.market.Game2Service;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import com.ispan.util.member.HibernateSession;

@MultipartConfig
@WebServlet("/DemoInsertPropServletAction")
public class DemoInsertPropServletAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		insertProp(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		insertProp(request, response);
	}

	private void insertProp(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		SessionFactory factory = HibernateSession.getFactory();
		Session session = null;
		int selectedGameId = Integer.parseInt(request.getParameter("gameId")); // 獲取選單的selectedGameId
		String propName = request.getParameter("propName");

		session = factory.getCurrentSession();
		System.out.println("Transaction started");
		Game2Service gService = new Game2Service(session);
		Game2 game = gService.findById(selectedGameId); // 通過選單的selectedGameId獲取遊戲物鍵

		if (game != null) {
			System.out.println("Game found: " + game.getGameName());
			Prop prop = new Prop();

			Date createdTime = new Date();
			prop.setCreatedTime(createdTime);
			prop.setGame2(game); // 將game物件引用PK直接存入prop
			prop.setGameId(game.getGameId());
			prop.setPropDescription(request.getParameter("propDescription"));
			prop.setPropImageName(request.getParameter("imageName"));
			prop.setPropName(request.getParameter("propName"));
			prop.setPropRarity(request.getParameter("propRarity"));
			prop.setPropType(request.getParameter("propType"));

			// 上傳圖片
			Part part = request.getPart("propImagePath");
			String filename = part.getSubmittedFileName();
			String uploadDir = "D:\\EEIT183-2ndGroup-Project\\ProjectWorkspace\\PlayCentric\\src\\main\\webapp\\images\\market\\";
			part.write(uploadDir + filename);
			prop.setPropImageName(filename);

			List<Prop> props = new LinkedList<>();
			props.add(prop);
			game.setProps(props);

			// 保存對象到數據庫
			session.persist(prop);
			System.out.println("Prop saved successfully");
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/DemoPropServletAction");
			dispatcher.forward(request, response);

		}
	}
}
