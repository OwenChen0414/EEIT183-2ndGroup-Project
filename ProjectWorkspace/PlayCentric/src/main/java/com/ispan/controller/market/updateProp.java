package com.ispan.controller.market;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ispan.bean.market.Game2;
import com.ispan.bean.market.Prop;
import com.ispan.dao.market.Game2Service;
import com.ispan.dao.market.PropService;

@Controller
public class updateProp {

    @Autowired
    private PropService propService;

    @Autowired
    private Game2Service game2Service;

    @GetMapping("/showUpdatePropForm")
    public String showUpdatePropForm(
            @RequestParam("gameId") int gameId,
            @RequestParam("propId") int propId,
            @RequestParam("propName") String propName,
            @RequestParam("propType") String propType,
            @RequestParam("propRarity") String propRarity,
            @RequestParam("propDescription") String propDescription,
            @RequestParam("propImageName") String propImageName,
            Model model) {
        
        model.addAttribute("gameId", gameId);
        model.addAttribute("propId", propId);
        model.addAttribute("propName", propName);
        model.addAttribute("propType", propType);
        model.addAttribute("propRarity", propRarity);
        model.addAttribute("propDescription", propDescription);
        model.addAttribute("propImagePath", propImageName);

        return "/market/updateProp";
    }
    
    @PostMapping("/updateProp")
    public String updateProp(
            @RequestParam("gameId") int gameId,
            @RequestParam("propId") int propId,
            @RequestParam("propName") String propName,
            @RequestParam("propType") String propType,
            @RequestParam("propRarity") String propRarity,
            @RequestParam("propDescription") String propDescription,
            @RequestParam("propImageName") MultipartFile propImageName,
            RedirectAttributes redirectAttributes,
            Model model) {
    	
        Game2 game2 = game2Service.findById(gameId);

        Prop prop = new Prop();
        prop.setGame2(game2);
        prop.setGameId(gameId);
        prop.setPropId(propId);
        prop.setPropName(propName);
        prop.setPropType(propType);
        prop.setPropRarity(propRarity);
        prop.setPropDescription(propDescription);

        Date updateTime = new Date();
        prop.setUpdatedTime(updateTime);

        if (!propImageName.isEmpty()) {
            try {
                String filename = propImageName.getOriginalFilename();
                String uploadDir = "D:\\EEIT183-2ndGroup-Project\\ProjectWorkspace\\PlayCentric\\src\\main\\webapp\\WEB-INF\\resources\\images\\market\\";
                propImageName.transferTo(new java.io.File(uploadDir + filename));
                prop.setPropImageName(filename);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        propService.update(prop);
		redirectAttributes.addAttribute("gameId", gameId);
        return "redirect:/ShowPropsByGameId"; 
     }
 }