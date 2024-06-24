package com.ispan.controller.playWithOthers;

import java.io.IOException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ispan.bean.playWithOthers.PwUser;
import com.ispan.dao.playWithOthers.PwUserService;
import com.ispan.util.member.HibernateSession;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//@WebServlet("/DemofuzzySearch")
@Controller
public class DemofuzzySearch {
//public class DemofuzzySearch extends HttpServlet {
//	private static final long serialVersionUID = 1L;

//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
	// 同樣先建立伺服
	@Autowired
	PwUserService pwUserService;

//和搜尋全部一樣的網置 用同個網址？
	//用post比較有隱私
	@PostMapping("/search")
	public String searchPwUserMsg(@RequestParam(name = "nickname", required = false) String nickname, Model model) {
												//網頁丟出的name變數別忘記了
		//		String nickname = request.getParameter("nickname");

//		response.setContentType("text/html;charset=UTF-8");
//		SessionFactory facotry = HibernateSession.getFactory();
//		Session session = facotry.getCurrentSession();
//		PwUserService pwUserService = new PwUserService(session);
		
		//原本傳遞空值可以找全部 springMVC不能用了 另外寫條件
		if (nickname == null || nickname.isEmpty()) {
			List<PwUser> pwUsers = pwUserService.findAll();
			model.addAttribute("pwUsers", pwUsers);
		}else {
			
			List<PwUser> pwUsers = pwUserService.findUserName(nickname);
			model.addAttribute("pwUsers", pwUsers);
		}

//		request.setAttribute("pwUsers", pwUsers);

//		request.getRequestDispatcher("/dynamicView/playWithOthers/GetAllUser.jsp").forward(request, response);
		return "/playWithOthers/GetAllUser";
	}
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		doGet(request, response);
//	}
}