package com.ispan.controller.member;

import org.springframework.web.bind.annotation.RequestMapping;

import com.ispan.bean.member.Member;

import jakarta.servlet.http.HttpServletRequest;

public class GenerateMemberBean {
       
	@RequestMapping("/GenerateMemberBean")
	protected String processAction(HttpServletRequest request) {
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
		
		return (String) request.getAttribute("function");
	}

}
