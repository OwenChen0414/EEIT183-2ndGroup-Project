package com.ispan.servlet;

import java.io.IOException;

import com.ispan.dao.MemberDao;
import com.ispan.util.CreateConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DelectMember")
public class DelectMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		int count = 0;
		try (CreateConnection createConnection = new CreateConnection()) {
			MemberDao memberDao = new MemberDao(createConnection);
			count = memberDao.deleteMember("mem_id", id);
		}
		String descript = count > 0 ? String.format("成功刪除%d筆資料!", count) : "刪除失敗!";
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
