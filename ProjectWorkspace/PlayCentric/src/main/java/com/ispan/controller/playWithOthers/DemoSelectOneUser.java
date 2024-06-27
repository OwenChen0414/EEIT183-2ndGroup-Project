package com.ispan.controller.playWithOthers;

import java.io.IOException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ispan.bean.playWithOthers.PwUser;
import com.ispan.dao.playWithOthers.PwUserService;
import com.ispan.util.member.HibernateSession;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//@WebServlet("/DemoSelectOneUser")
//public class DemoSelectOneUser extends HttpServlet {
//	private static final long serialVersionUID = 1L;
@Controller
public class DemoSelectOneUser{
	
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
	@Autowired
	PwUserService pwUserService;
	
	@GetMapping("/sel")
	public String selectOnePwUser(@RequestParam(name = "id")Integer id,Model model) {
	
//		String id = request.getParameter("id");
//		response.setContentType("text/html;charset=UTF-8");
//		SessionFactory facotry = HibernateSession.getFactory();
//		Session session = facotry.getCurrentSession();
		
	
	
//		PwUserService pwUserService = new PwUserService(session);

//		PwUser user = pwUserService.findUserId(Integer.parseInt(id));
	
//		request.setAttribute("PwUser", user);
		
	//呼叫bean 給id叫出對應值存到pwUser
		PwUser pwUser = pwUserService.findUserId(id);
		model.addAttribute("PwUser", pwUser);
		//model出來對應的值
//		request.getRequestDispatcher("/dynamicView/playWithOthers/OneUser.jsp").forward(request, response);
    return "/playWithOthers/OneUser";
	}

//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		doGet(request, response);
//	}
}