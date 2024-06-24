package com.ispan.controller.market;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ispan.bean.market.Game2;
import com.ispan.bean.market.Prop;
import com.ispan.dao.market.Game2Service;
import com.ispan.dao.market.PropService;

@Controller
public class propController {

	@Autowired
	private PropService propService;

	@Autowired
	private Game2Service game2Service;

	@GetMapping("/insertPropForm")
	public String showInsertPropForm(Model model) {
		model.addAttribute("games", game2Service.findAll());
		return "market/insertProp";
	}

	// 新增
	@PostMapping("/insertProp")
	public String insertProp(@RequestParam("gameId") int gameId,
			@RequestParam("propDescription") String propDescription, @RequestParam("propName") String propName,
			@RequestParam("propRarity") String propRarity, @RequestParam("propType") String propType,
			@RequestPart("propImage") MultipartFile propImage, RedirectAttributes redirectAttributes) {

		try {
			Game2 game = game2Service.findById(gameId); // 通過選單的GameId獲取遊戲物件

			Prop prop = new Prop();
			Date createdTime = new Date();
			prop.setCreatedTime(createdTime);
			prop.setGame2(game); // 將game物件引用PK直接存入prop
			prop.setGameId(game.getGameId());
			prop.setPropDescription(propDescription);
			prop.setPropName(propName);
			prop.setPropRarity(propRarity);
			prop.setPropType(propType);

			// 上傳圖片
			String filename = propImage.getOriginalFilename();
			String uploadDir = "D:\\EEIT183-2ndGroup-Project\\ProjectWorkspace\\PlayCentric\\src\\main\\webapp\\WEB-INF\\resources\\images\\market\\";
			Path uploadPath = Paths.get(uploadDir);
			if (!Files.exists(uploadPath)) {
				Files.createDirectories(uploadPath);
			}
			Path filePath = uploadPath.resolve(filename);
			propImage.transferTo(filePath.toFile());

			prop.setPropImageName(filename);

			// 保存對象到數據庫
			propService.insert(prop);

			// 将 gameId 添加到重定向属性中
			redirectAttributes.addAttribute("gameId", gameId);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return "redirect:/ShowPropsByGameId"; // 替換為實際需要跳轉的URL
	}

	// 修改
	@GetMapping("/showUpdatePropForm")
	public String showUpdatePropForm(@RequestParam("propId") int propId, Model model) {

		Prop originProp = propService.findById(propId);
		model.addAttribute("prop", originProp);
		return "/market/updateProp";
	}

	@PostMapping("/updateProp")
	public String updateProp(

			@RequestParam("propImage") MultipartFile propImage, @ModelAttribute Prop prop,
			RedirectAttributes redirectAttributes, Model model) {

		Prop originProp = propService.findById(prop.getPropId());
		Game2 game2 = originProp.getGame2();
		prop.setGame2(game2);

		Date updateTime = new Date();
		prop.setUpdatedTime(updateTime);
		if (!propImage.isEmpty()) {
			try {
				String filename = propImage.getOriginalFilename();
				String uploadDir = "D:\\EEIT183-2ndGroup-Project\\ProjectWorkspace\\PlayCentric\\src\\main\\webapp\\WEB-INF\\resources\\images\\market\\";
				propImage.transferTo(new java.io.File(uploadDir + filename));
				prop.setPropImageName(filename);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		propService.update(prop);
		redirectAttributes.addAttribute("gameId", game2.getGameId());
		return "redirect:/ShowPropsByGameId";
	}

	// 刪除
	@GetMapping("/DeleteProp")
	public String showInsertPropForm(@RequestParam("propId") int propId, @RequestParam("gameId") int gameId,
			RedirectAttributes redirectAttributes, Model model) {

		propService.deleteById(propId);
		redirectAttributes.addAttribute("gameId", gameId);
		return "redirect:/ShowPropsByGameId";

	}

	// 根據gameId查詢全部道具

	@GetMapping("/ShowPropsByGameId")
	public String selectProps(@RequestParam("gameId") int gameId, Model model) {
		List<Game2> games = game2Service.findAll();

		List<Prop> props = propService.findPropsByGameId(gameId);
		model.addAttribute("props", props);
		model.addAttribute("games", games);
		model.addAttribute("selectedGameId", gameId);
		return "/market/propSheet";
	}

	// 模糊查詢
	@GetMapping("/FuzzySearchProp")
	public String fuzzySearchProp(@RequestParam("selectedGameId") int selectedGameId,
			@RequestParam(value = "searchPropName", required = false) String searchPropName, Model model) {
		List<Prop> props = propService.findPropsByGameId(selectedGameId);
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
