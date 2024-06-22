package com.ispan.controller.market;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

@Controller
@SessionAttributes(names = "fuzzySearchProps")
public class FuzzySearchProp {

	@Autowired
	private PropService propService;

	@Autowired
	private Game2Service game2Service;

	@GetMapping("/FuzzySearchProp")
	public String fuzzySearchProp(@RequestParam("selectedGameId") int selectedGameId,
			@RequestParam(value = "searchPropName", required = false) String searchPropName, Model model) {
		List<Prop> props = propService.findSelectedProps(selectedGameId);
		List<Prop> searchOKprops;
		boolean noResults = false;

		searchOKprops = props.stream().filter(prop -> prop.getPropName().contains(searchPropName))
				.collect(Collectors.toList());
		if (searchOKprops.isEmpty()) {
			noResults = true;
		}

		List<Game2> games = game2Service.findAll();
		model.addAttribute("props", props);
		model.addAttribute("searchOKprops", searchOKprops);
		model.addAttribute("games", games);
		model.addAttribute("selectedGameId", selectedGameId);
		model.addAttribute("noResults", noResults);
		return "/market/propSheet";
	}
}
