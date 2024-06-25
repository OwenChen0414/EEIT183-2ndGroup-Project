package com.ispan.controller.member;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import jakarta.servlet.ServletException;

@Controller
@SessionAttributes(names = "member")
public class LogoutController {
       
	@GetMapping("/Logout")
	protected String doGet(SessionStatus sessionStatus) throws ServletException, IOException {
		sessionStatus.setComplete();
		return "redirect:member/home";
	}

}
