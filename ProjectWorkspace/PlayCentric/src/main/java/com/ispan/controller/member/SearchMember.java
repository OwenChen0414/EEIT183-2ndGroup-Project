package com.ispan.controller.member;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Session;

import com.ispan.bean.member.MemView;
import com.ispan.dao.member.MemberDao;
import com.ispan.util.member.HibernateSession;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/SearchMember")
public class SearchMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String,String> searchList = new HashMap<>();
		searchList.put("memId",request.getParameter("id"));
		searchList.put("account",request.getParameter("account"));
		searchList.put("passwords",request.getParameter("password"));
		searchList.put("email",request.getParameter("email"));
		searchList.put("nickname",request.getParameter("nickName"));
		searchList.put("memName",request.getParameter("memName"));
		searchList.put("birthday",request.getParameter("birthday"));
		searchList.put("phone",request.getParameter("phone"));
		searchList.put("addres",request.getParameter("address"));
		searchList.put("consumption",request.getParameter("consumption"));
		searchList.put("registDate",request.getParameter("registDate"));
		searchList.put("lastLoginTime",request.getParameter("lastLogin"));
		searchList.put("roles",request.getParameter("role"));
		searchList.put("levels",request.getParameter("level"));
		for(Object entry : searchList.entrySet()) {
			Entry<String,String> e = (Entry<String,String>) entry;
			System.out.println(e.getKey()+":"+e.getValue());
		}

		List<MemView> members = new ArrayList<>();

		{
			Session session = HibernateSession.getFactory().getCurrentSession();
			MemberDao memberDao = new MemberDao(session);
			members = memberDao.selectMembers(searchList);
		}
		request.setAttribute("members", members);
		request.getRequestDispatcher("/dynamicView/member/MemberSheet.jsp")
			.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
