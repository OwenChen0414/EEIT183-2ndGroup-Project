package com.ispan.controller.playWithOthers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ispan.bean.playWithOthers.PwUser;
import com.ispan.dao.playWithOthers.PwUserService;
import com.ispan.util.member.HibernateSession;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DemoPwFindAll")
public class DemoPwFindAll extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		SessionFactory facotry = HibernateSession.getFactory();
		Session session = facotry.getCurrentSession();

		PwUserService pwUserService = new PwUserService(session);
		List<PwUser> pwUsers = pwUserService.findAll();
		request.setAttribute("pwUsers", pwUsers);
		request.getRequestDispatcher("/dynamicView/playWithOthers/GetAllUser.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
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
//		Session session = facotry.getCurrentSession();
//		
//		
//		
//		PwUserService pwUserService = new PwUserService(session);
//		List<PwUser> lists = pwUserService.findAll();
//		for(PwUser pwBean:lists) {
//			out.write(pwBean.getId() + " " + pwBean.getNickname() +" "+pwBean.getAmount()+ " "+pwBean.getDescription()+ " "+pwBean.getEditedTime()+ " "
//			+pwBean.getGameId()+ " "+pwBean.getOfflineTime()+ " "+pwBean.getOnlineTime()+ " "+pwBean.getPlayerPhoto()+ " "+pwBean.getPricingCategory()+ " "+pwBean.getTransactionStatus()+ " "+ "<br/>");
//		}
//		
//		out.close();
//	}

}
