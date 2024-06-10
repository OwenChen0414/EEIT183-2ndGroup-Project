package com.ispan.controller.member;

import java.io.IOException;

import com.ispan.bean.member.Member;
import com.ispan.dao.member.MemberDao;
import com.ispan.util.member.CreateConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/InsertMember")
public class InsertMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/GenerateMemberBean").include(request, response);
		int count = 0;
		try (CreateConnection createConnection = new CreateConnection()) {
			MemberDao memberDao = new MemberDao(createConnection);
			Member member = (Member)request.getAttribute("member");
			count = memberDao.saveMembers(member);
		}
		String descript = count > 0 ? String.format("成功新增%d筆資料!", count) : "新增失敗!";
		request.setAttribute("descript", descript);
		request.getRequestDispatcher("/MemberManage").forward(request, response);
//		String url = (String) request.getSession().getAttribute("lastPage");
//		url = url + (url.contains("?") ? "" : "?") + "descipt=" + descript;
//		response.sendRedirect(url);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
