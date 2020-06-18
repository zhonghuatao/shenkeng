package com.tao.shenkeng.controller;

import com.tao.shenkeng.dto.PaginationDTO;
import com.tao.shenkeng.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;
    //page表示当前页面，size表示每页显示5条数据
    @GetMapping("/")
    public String hello(
                        Model model,
                        @RequestParam(name = "page",defaultValue = "1")Integer page,
                        @RequestParam(name = "size",defaultValue = "4")Integer size) {
        PaginationDTO pagination=questionService.list(page,size);
        PaginationDTO maxView = questionService.MaxView();
        model.addAttribute("pagination",pagination);
        model.addAttribute("maxView",maxView);
        return "index";
    }
}
