package com.ispan.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ispan.bean.Member;
import com.ispan.dao.MemberDao;
import com.ispan.util.CreateConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/MemberManage")
public class MemberManage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Member> members = new ArrayList<Member>();
		
		try (CreateConnection createConnection = new CreateConnection()) {
			MemberDao memberDao = new MemberDao(createConnection);
			members = memberDao.findAllMembers();
		}
		request.setAttribute("members", members);
		request.getRequestDispatcher("/JSP/MemberSheet.jsp")
			.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
