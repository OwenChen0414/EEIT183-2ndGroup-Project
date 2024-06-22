package com.ispan.controller.market;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ispan.bean.market.Game2;
import com.ispan.dao.market.Game2Service;

@Controller
@SessionAttributes(names = "game2")
public class DemoGameServletAction {

    @Autowired
    private Game2Service game2Service;

    @GetMapping("/SelectGame2")
    public String doGet(Model model) {
        List<Game2> games = game2Service.findAll();
        // 將數據添加到模型中
        model.addAttribute("games", games);

        // 返回視圖名稱，Spring MVC 會根據視圖解析器解析並轉發到相應的頁面
        return "/market/propSheet";
    }
}
