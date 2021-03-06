package com.tao.shenkeng.controller;

import com.tao.shenkeng.dto.PaginationDTO;
import com.tao.shenkeng.dto.QuestionDTO;
import com.tao.shenkeng.model.Question;
import com.tao.shenkeng.model.User;
import com.tao.shenkeng.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private QuestionService questionService;


    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id") Long id,
                       Model model){
        PaginationDTO maxView = questionService.MaxView();
        model.addAttribute("maxView",maxView);
        QuestionDTO question = questionService.getById(id);
        model.addAttribute("title",question.getTitle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag",question.getTag());
        return "/publish";
    }

    @GetMapping("/publish")
    public String publish(Model model){
        PaginationDTO maxView = questionService.MaxView();
        model.addAttribute("maxView",maxView);
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(value = "title",required = false) String title,
            @RequestParam(value = "description",required = false) String description,
            @RequestParam(value = "tag",required = false) String tag,
            @RequestParam(value = "id",required = false) Long id,
            Model model,
            HttpServletRequest request ){

        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);

        if(user == null){
            model.addAttribute("error","用户未登录");
            return "publish";
        }
        if(title==null||title==""){
            model.addAttribute("error","标题不能为空");
            return "publish";
        }
        if(description==null||description==""){
            model.addAttribute("error","问题补充不能为空");
            return "publish";
        }
        if(tag==null||tag==""){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }

        Question question=new Question();
        question.setId(id);
        question.setTitle(title);
        question.setTag(tag);
        question.setDescription(description);
        question.setCreator(user.getId());
        questionService.createOrUpDate(question);
        return "redirect:/";
    }
}
