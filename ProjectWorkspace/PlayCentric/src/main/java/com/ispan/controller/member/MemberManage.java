package com.ispan.controller.member;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.ispan.bean.member.MemView;
import com.ispan.dao.member.MemberDao;
import com.ispan.util.member.HibernateSession;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/MemberManage")
public class MemberManage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<MemView> members = new ArrayList<>();

		{
			Session session = HibernateSession.getFactory().getCurrentSession();
			MemberDao memberDao = new MemberDao(session);
			members = memberDao.findAllMembers();
		}
		request.setAttribute("members", members);
		request.getRequestDispatcher("/dynamicView/member/MemberSheet.jsp")
			.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
