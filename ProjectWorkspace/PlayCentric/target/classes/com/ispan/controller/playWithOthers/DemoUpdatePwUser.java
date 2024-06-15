package com.ispan.controller.playWithOthers;

import java.io.IOException;
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

@WebServlet("/DemoUpdatePwUser")
@MultipartConfig(location = "D:\\EEIT183-2ndGroup-Project\\ProjectWorkspace\\PlayCentric\\src\\main\\webapp\\images\\playWithOthers")
public class DemoUpdatePwUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        String id = request.getParameter("id");
		String nickname = request.getParameter("nickname");
		String gameId = request.getParameter("gameId");
		String pricingCategory = request.getParameter("pricingCategory");
		String amount = request.getParameter("amount");
		String onlineTime = request.getParameter("onlineTime");
		String offlineTime = request.getParameter("offlineTime");
		String transactionStatus = request.getParameter("transactionStatus");
		String description = request.getParameter("description");
		Part photo = request.getPart("playerPhoto");

		String newFilename = getFileName();
		photo.write(newFilename);
		PwUser user = new PwUser();
		user.setId(Integer.parseInt(id));
		user.setNickname(nickname);
		user.setGameId(Integer.parseInt(gameId));
		user.setPricingCategory(pricingCategory);
		user.setAmount(Integer.parseInt(amount));
		user.setOnlineTime(onlineTime);
		user.setOfflineTime(offlineTime);
		user.setPlayerPhoto(newFilename);
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
		pwUserService.update(user);
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