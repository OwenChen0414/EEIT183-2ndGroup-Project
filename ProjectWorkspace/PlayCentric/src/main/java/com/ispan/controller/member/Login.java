package com.ispan.controller.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ispan.bean.member.Member;
import com.ispan.dao.member.MemberService;

@Controller
@SessionAttributes(names = "member")
public class Login{
	
	@Autowired
	private MemberService memberService;

	@PostMapping("/Login")
	protected String doPost(@RequestParam(name = "account") String account,@RequestParam(name = "password") String password, Model model){
		Member member = new Member();
		member.setAccount(account);
		member.setPassword(password);

		member = memberService.checkLogin(member);
		if (member == null) {
			model.addAttribute("role", "guest");
			return "member/login";
		}
		model.addAttribute("member", member);
		return "redirect:Home";
	}

	@GetMapping("/Login")
	protected String doGet(){
		return "member/login";
	}

}
