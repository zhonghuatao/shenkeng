package com.tao.shenkeng.controller;

import com.tao.shenkeng.dto.CommentViewDTO;
import com.tao.shenkeng.dto.QuestionDTO;
import com.tao.shenkeng.service.CommentService;
import com.tao.shenkeng.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @Autowired
    CommentService commentService;
    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Long id,
                           Model model) {
        QuestionDTO question = questionService.getById(id);
        questionService.incView(id);
        List<CommentViewDTO> commentViewDTOs=commentService.ListByQuestionId(id);
        model.addAttribute("question", question);
        model.addAttribute("commentViewDTOs",commentViewDTOs);
        return "question";
    }
}
