package com.ispan.controller.texts;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ispan.bean.texts.Texts;
import com.ispan.dao.texts.TextsService;

@Controller
public class TextsController {

	@Autowired
	private TextsService textsService;

	@GetMapping("/findAllTexts")
	public String listTexts(Model model) {
		List<Texts> txts = textsService.getAllTexts();
		model.addAttribute("txts", txts);
		return "texts/getAllTexts2";
	}

	@GetMapping("/addTexts")
	public String showAddTextsForm() {
//	        model.addAttribute("txt", new Texts());
		return "texts/insertText2";
	}

	@PostMapping("texts/insertTexts")
	public String insertTexts(@RequestParam("forumId") Integer forumId, 
	                          @RequestParam("memId") Integer memId, 
	                          @RequestParam("title") String title,
	                          @RequestParam("textsContent") String textsContent, 
	                          @RequestParam("doneTime") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime doneTime, 
	                          @RequestParam("updatedTime") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime updatedTime,
	                          @RequestParam("textsLikeNum") Integer textsLikeNum, 
	                          @RequestParam(value = "hideTexts", required = false) Boolean hideTexts) {
	        
		if (hideTexts == null) {
	        hideTexts = false;
	    }
	        
	        Texts txt = new Texts();
	        txt.setForumId(forumId);
	        txt.setMemId(memId);
	        txt.setTitle(title);
	        txt.setTextsContent(textsContent);
	        txt.setDoneTime(doneTime);  // 設置字符串
	        txt.setUpdatedTime(updatedTime);  // 設置字符串
	        txt.setTextsLikeNum(textsLikeNum);
	        txt.setHideTexts(hideTexts);

	        textsService.insertTexts(txt);

	    return "redirect:/findAllTexts";
	}

	@GetMapping("/findTexts")
	public String showTexts() {
		return "texts/getText2";
	}

	@PostMapping("texts/getText")
	public String showTexts(@RequestParam("textsId") Integer textsId, Model model) {
		System.out.println(textsId);
		Texts txt = textsService.getTextsById(textsId);
		System.out.println(txt);
		model.addAttribute("txt", txt);
		return "texts/getText3";
	}

	@GetMapping("/editTexts")
	public String editTexts() {
		return "texts/updateText2";
	}

	@PostMapping("texts/updateTexts")
	public String updateTexts(@RequestParam("textsId") Integer textsId, @ModelAttribute Texts txt) {
		txt.setTextsId(textsId);
		textsService.updateTexts(txt);
		return "redirect:/findAllTexts";
	}

	@GetMapping("/destroyTexts")
	public String destroyTexts() {
		return "texts/deleteText2";
	}

	@PostMapping("texts/deleteText")
	public String deleteTexts(@RequestParam("textsId") Integer textsId) {
		textsService.deleteTexts(textsId);
		return "redirect:/findAllTexts";
	}

}
