package com.ispan.controller.market;

import java.io.IOException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ispan.dao.market.PropService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.ispan.util.member.HibernateSession;

@Controller
public class DeleteProp {
	
	@Autowired
	private PropService propService;
	
	@GetMapping("/DeleteProp")
	public String showInsertPropForm(@RequestParam("propId") int propId , 
			@RequestParam("gameId") int gameId,
			RedirectAttributes redirectAttributes,
			Model model) {
		
		propService.deleteById(propId);
		redirectAttributes.addAttribute("gameId", gameId);
		return "redirect:/ShowPropsByGameId";

	}
}
