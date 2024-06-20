package com.ispan.controller.member;

import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ispan.bean.member.Member;
import com.ispan.dao.member.MemberDao;
import com.ispan.util.member.HibernateSession;

@Controller
@SessionAttributes(names = "member")
public class Login{

	@PostMapping("/Login")
	protected String doPost(@RequestParam String account,@RequestParam String password, Model model){
		Member member = new Member();
		member.setAccount(account);
		member.setPassword(password);

		{
			Session session = HibernateSession.getFactory().getCurrentSession();
			MemberDao memberDao = new MemberDao(session);
			member = memberDao.checkLogin(member);
		}
		if (member == null) {
			model.addAttribute("role", "guest");
			return "member/login";
		}
		model.addAttribute("member", member);
		return "redirect:member/home";
	}

	@GetMapping("/Login")
	protected String doGet(){
		return "member/login";
	}

}
