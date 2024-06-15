package com.ispan.controller.playWithOthers;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ispan.bean.playWithOthers.PwUser;
import com.ispan.dao.playWithOthers.PwUserService;
import com.ispan.util.member.HibernateSession;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/DemoInsertPwUser")
@MultipartConfig(location = "D:\\EEIT183-2ndGroup-Project\\ProjectWorkspace\\PlayCentric\\src\\main\\webapp\\images\\playWithOthers")
public class DemoInsertPwUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Part photo = request.getPart("photo");
		String nickname = request.getParameter("nickname");
		String gameId = request.getParameter("gameId");
		String pricingCategory = request.getParameter("pricingCategory");
		String amount = request.getParameter("amount");
		String onlineTime = request.getParameter("onlineTime");
		String offlineTime = request.getParameter("offlineTime");
		String transactionStatus = request.getParameter("transactionStatus");
		String description = request.getParameter("description");
		// 取照片用的
		
	
		

		String filename = getFileName();
		photo.write(filename);
		PwUser user = new PwUser();
		
		
		
		
		user.setNickname(nickname);
		user.setGameId(Integer.parseInt(gameId));
		user.setPricingCategory(pricingCategory);
		user.setAmount(Integer.parseInt(amount));
		user.setOnlineTime(onlineTime);
		user.setOfflineTime(offlineTime);
		user.setPlayerPhoto(filename);
		user.setTransactionStatus(transactionStatus);
		user.setDescription(description);
		
		// 創建時間
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedDateTime = now.format(formatter);
		user.setEditedTime(formattedDateTime);

		SessionFactory facotry = HibernateSession.getFactory();
		Session session = facotry.getCurrentSession();
		PwUserService pwUserService = new PwUserService(session);
		pwUserService.insert(user);

		request.getRequestDispatcher("/DemoPwFindAll").forward(request, response);
	}

	public String getFileName() {
	    String randomString = UUID.randomUUID().toString();
		String newFileName = randomString + ".jpg";
		return newFileName;
	}
	 
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
//	@Override
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		processAction(request, response);
//	}
//
//	@Override
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		processAction(request, response);
//	}
//
//	private void processAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		response.setContentType("text/html;charset=UTF-8");
//		PrintWriter out = response.getWriter();
//		
//		SessionFactory facotry = HibernateUtil.getSessionFactory();
//		Session session = facotry.getCurrentSession();//getCurrentSession用來綁定上下文的Session 一次合併 提交或rollback就自動關閉
//		PwUserService pwUserService = new PwUserService(session);
//		PwUser pwUser = new PwUser("NickName1", 1, "小時制", 100, "12:00:00", "18:00:00", "aaa.jpg", "我是漂亮的大學生","2024-06-11 21:27:45.800","閒置");
//		PwUser pwUser2 = new PwUser("NickName1", 2, "小時制", 100, "12:00:00", "18:00:00", "bbb.jpg", "我是漂亮的外國大學生","2024-06-11 21:27:45.800","閒置");
//		
//		pwUserService.insert(pwUser);
//		pwUserService.insert(pwUser2);
//		out.print("新增成功");
//		
//		out.close();
//	}

//}
