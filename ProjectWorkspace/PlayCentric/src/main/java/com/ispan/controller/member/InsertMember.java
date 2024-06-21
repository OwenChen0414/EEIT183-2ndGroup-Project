package com.ispan.controller.member;

import java.io.IOException;

import org.hibernate.Session;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;

import com.ispan.bean.member.Member;
import com.ispan.dao.member.MemberDao;
import com.ispan.dao.member.MemberService;
import com.ispan.util.member.HibernateSession;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class InsertMember {
	private MemberService memberService;
       
	@GetMapping("/InsertMember")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/GenerateMemberBean").include(request, response);
		int count = 0;
		{
			Member member = (Member)request.getAttribute("member");
			count = memberDao.saveMember(member);
		}
		String descript = count > 0 ? String.format("成功新增%d筆資料!", count) : "新增失敗!";
		request.setAttribute("descript", descript);
		request.getRequestDispatcher("/MemberManage").forward(request, response);
//		String url = (String) request.getSession().getAttribute("lastPage");
//		url = url + (url.contains("?") ? "" : "?") + "descipt=" + descript;
//		response.sendRedirect(url);
	}

	@PatchMapping("/InsertMember")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
