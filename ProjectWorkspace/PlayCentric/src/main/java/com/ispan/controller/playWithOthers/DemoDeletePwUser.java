package com.ispan.controller.playWithOthers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

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

//@WebServlet("/DemoDeletePwUser")
@Controller
public class DemoDeletePwUser {
//public class DemoDeletePwUser extends HttpServlet {
//	private static final long serialVersionUID = 1L;

	PwUserService pwUserService;

	@Autowired
	public DemoDeletePwUser(PwUserService pwUserService) {
		this.pwUserService = pwUserService;
	}

//只是id看不到重要資料 就用getㄅ
	@GetMapping("/del")
	// 別忘了jsp的action也要一樣
	public String deletePwUser(@RequestParam(name = "id", required = false) Integer id, Model model) {

//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		String id = request.getParameter("id");
//
//		SessionFactory facotry = HibernateSession.getFactory();
//		Session session = facotry.getCurrentSession();

//		PwUserService pwUserService = new PwUserService(session);
		pwUserService.deleteUser(id);
		List<PwUser> pwUsers = pwUserService.findAll();

		model.addAttribute("pwUsers", pwUsers);
//		pwUserService.deleteUser(Integer.parseInt(id));

//	request.getRequestDispatcher("/DemoPwFindAll").forward(request, response);
		// 會把值帶到頁面 所以model要重新設定圖 不然會變成找不到被刪除的人資料
		return "/playWithOthers/getAllUser";
	}
}

//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		doGet(request, response);
//	}

//	@Override
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		processAction(request, response);
//	}
//
//	@Override
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		processAction(request, response);
//	}
//
//	private void processAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		response.setContentType("text/html;charset=UTF-8");
//		PrintWriter out = response.getWriter();
//
//		SessionFactory facotry = HibernateUtil.getSessionFactory();
//		Session session = facotry.getCurrentSession();
//
//		PwUserService pwUserService = new PwUserService(session);
//		int deleteId = 1;
//		pwUserService.deleteUser(deleteId);
//		System.out.println("刪除成功");
//
//		out.close();
//	}

//}
