package com.ispanwei.project1crud;

import java.io.IOException;
import com.ispanwei.bean.PlayUserBean;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/CreateUser")
@MultipartConfig(location = "D:\\testWorkspace\\playWithOthers\\src\\main\\webapp\\image")
public class CreateUser extends HttpServlet {
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
		String filename = getFileName(photo, nickname);

		photo.write(filename);

		PlayUserBean user = new PlayUserBean();
		user.setNickname(nickname);
		user.setGameId(Integer.parseInt(gameId));
		user.setPricingCategory(pricingCategory);
		user.setAmount(Integer.parseInt(amount));
		// 轉換成時間格式
		user.setOnlineTime(onlineTime);
		user.setOfflineTime(offlineTime);
		user.setPlayerPhoto(filename);
		user.setTransactionStatus(transactionStatus);
		user.setDescription(description);


		PwDAO pwDAO = new PwDAO();
		try {
            pwDAO.createUser(user);

		} catch (Exception e) {
			e.printStackTrace();
		}
        request.getRequestDispatcher("/Project1/CreateUser2.jsp").forward(request, response);
	}

	private String getFileName(Part photo, String nickname) {

		String newFileName = nickname + ".jpg";
		return newFileName;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}