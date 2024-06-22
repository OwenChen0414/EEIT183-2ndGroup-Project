package com.ispan.controller.market;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.RequestDispatcher;

import java.io.IOException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ispan.bean.market.Game2;
import com.ispan.bean.market.Prop;
import com.ispan.dao.market.Game2Service;
import com.ispan.dao.market.PropService;
import com.ispan.util.member.HibernateSession;

@Controller
@SessionAttributes(names = "props")
public class ShowPropsByGameId {

    @Autowired
    private PropService propService;
    
    @Autowired
    private Game2Service game2Service;
    
    
    @GetMapping("/ShowPropsByGameId")
    public String selectProps(@RequestParam("gameId") int gameId, Model model) {
        List<Game2> games = game2Service.findAll();
        
    	List<Prop> props = propService.findPropsByGameId(gameId);
        model.addAttribute("props", props);
        model.addAttribute("games", games);
        model.addAttribute("selectedGameId", gameId);
        return "/market/propSheet"; 
    }
}
