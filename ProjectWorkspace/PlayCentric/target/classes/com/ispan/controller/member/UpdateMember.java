package com.ispan.controller.member;

import java.io.IOException;

import org.hibernate.Session;

import com.ispan.bean.member.Member;
import com.ispan.dao.member.MemberDao;
import com.ispan.util.member.HibernateSession;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/UpdateMember")
public class UpdateMember extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/GenerateMemberBean").include(request, response);

//		request.getRequestDispatcher("/JSP/GetEmp.jsp").forward(request, response);
		int count = 0;
		{
			Session session = HibernateSession.getFactory().getCurrentSession();
			MemberDao memberDao = new MemberDao(session);
			Member member = (Member) request.getAttribute("member");
			count = memberDao.updateMember(member);
		}
		String descript = count > 0 ? String.format("成功更新%d筆資料!", count) : "更改失敗!";
		request.setAttribute("descript", descript);
		request.getRequestDispatcher("/MemberManage").forward(request, response);
//		String url = (String) request.getSession().getAttribute("lastPage");
//		url = url + (url.contains("?") ? "" : "?") + "descipt=" + descript;
//		response.sendRedirect(url);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
