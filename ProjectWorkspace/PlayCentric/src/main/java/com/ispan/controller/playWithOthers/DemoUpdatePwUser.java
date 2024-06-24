package com.ispan.controller.playWithOthers;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.UUID;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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

//@WebServlet("/DemoUpdatePwUser")
//@MultipartConfig(location = "D:\\EEIT183-2ndGroup-Project\\ProjectWorkspace\\PlayCentric\\src\\main\\webapp\\images\\playWithOthers")

@Controller
public class DemoUpdatePwUser {
//	public class DemoUpdatePwUser extends HttpServlet {
//		private static final long serialVersionUID = 1L;
	@Autowired
	PwUserService pwUserService;

	// 應該也是和insert的方式差不多
	@PostMapping("/update") // jsp記得要改name again!!
	public String insertPwUser(@RequestParam Map<String, String> pwuser, @RequestParam("photo") MultipartFile photo)
			throws IOException {
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//        String id = request.getParameter("id");
//		String nickname = request.getParameter("nickname");
//		String gameId = request.getParameter("gameId");
//		String pricingCategory = request.getParameter("pricingCategory");
//		String amount = request.getParameter("amount");
//		String onlineTime = request.getParameter("onlineTime");
//		String offlineTime = request.getParameter("offlineTime");
//		String transactionStatus = request.getParameter("transactionStatus");
//		String description = request.getParameter("description");
//		Part photo = request.getPart("playerPhoto");
		String id = pwuser.get("id");
		String nickname = pwuser.get("nickname");
		String gameId = pwuser.get("gameId");
		String pricingCategory = pwuser.get("pricingCategory");
		String amount = pwuser.get("amount");
		String onlineTime = pwuser.get("onlineTime");
		String offlineTime = pwuser.get("offlineTime");
		String transactionStatus = pwuser.get("transactionStatus");
		String description = pwuser.get("description");

		// 取照片用的
		String filename = getFileName();
//				photo.write(filename);
		photo.transferTo(new File(
				"D:\\EEIT183-2ndGroup-Project\\ProjectWorkspace\\PlayCentric\\src\\main\\webapp\\WEB-INF\\resources\\images\\playWithOthers\\"
						+ filename));
//		String newFilename = getFileName();
//		photo.write(newFilename);
//		PwUser user = new PwUser();
//		user.setId(Integer.parseInt(id));
//		user.setNickname(nickname);
//		user.setGameId(Integer.parseInt(gameId));
//		user.setPricingCategory(pricingCategory);
//		user.setAmount(Integer.parseInt(amount));
//		user.setOnlineTime(onlineTime);
//		user.setOfflineTime(offlineTime);
//		user.setPlayerPhoto(newFilename);
//		user.setTransactionStatus(transactionStatus);
//		user.setDescription(description);
		PwUser user = new PwUser();
		user.setId(Integer.parseInt(id));
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

//		SessionFactory facotry = HibernateSession.getFactory();
//		Session session = facotry.getCurrentSession();

//		PwUserService pwUserService = new PwUserService(session);
		pwUserService.update(user);
//		request.getRequestDispatcher("/DemoPwFindAll").forward(request, response);
	    return "redirect:/Pw";

	}

	public String getFileName() {
		String randomString = UUID.randomUUID().toString();
		String newFileName = randomString + ".jpg";
		return newFileName;
	}

//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		doGet(request, response);
//	}
}