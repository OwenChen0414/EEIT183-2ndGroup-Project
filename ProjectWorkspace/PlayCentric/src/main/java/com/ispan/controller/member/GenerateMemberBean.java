package com.ispan.controller.member;

import java.io.IOException;

import com.ispan.bean.member.Member;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/GenerateMemberBean")
public class GenerateMemberBean extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Member member = new Member();
		member.setMemId(request.getParameter("id"));
		member.setAccount(request.getParameter("account"));
		member.setPassword(request.getParameter("password"));
		member.setEmail(request.getParameter("email"));
		member.setNickName(request.getParameter("nickName"));
		member.setMemName(request.getParameter("memName"));
		member.setBirthday(request.getParameter("birthday"));
		member.setPhone(request.getParameter("phone"));
		member.setAddress(request.getParameter("address"));
		member.setSso(request.getParameter("sso"));
		member.setAccomAcnt(request.getParameter("accomAccount"));
		member.setConsumption(request.getParameter("consumption"));
		member.setRegistDate(request.getParameter("registDate"));
		member.setLastLoginTime(request.getParameter("lastLogin"));
		member.setRole(request.getParameter("role"));
		member.setLevels(request.getParameter("level"));
		
		if (member.getRole().isEmpty()) {
			member.setRole("member");
		}
		if (member.getAccount().isEmpty()) {
			member.setAccount("defAcc");
		}
		if (member.getPassword().isEmpty()) {
			member.setPassword("123");
		}
		if (member.getBirthday().isEmpty()) {
			member.setBirthday(null);
		}
		if (member.getPhone().isEmpty()) {
			member.setPhone(null);
		}
		if (member.getAccomAcnt().isEmpty()) {
			member.setAccomAcnt(null);
		}
		
		request.setAttribute("member",member);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
