package com.tao.shenkeng.controller;

import com.tao.shenkeng.dto.CommentDTO;
import com.tao.shenkeng.dto.ResultDTO;
import com.tao.shenkeng.exception.CustomizeErrorCode;
import com.tao.shenkeng.model.Comment;
import com.tao.shenkeng.model.User;
import com.tao.shenkeng.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;


    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object post(@RequestBody CommentDTO commentDTO,
                       HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if(user==null){
            return ResultDTO.errorOf(CustomizeErrorCode.NOT_LOGIN);
        }
        if(commentDTO.getContent()==null||commentDTO.getContent().isEmpty()){
            return ResultDTO.errorOf(CustomizeErrorCode.comment_is_empty);
        }
        Comment comment=new Comment();
        comment.setGmt_create(System.currentTimeMillis());
        comment.setGmt_modified(System.currentTimeMillis());
        comment.setLike_count((long) 1);
        comment.setContent(commentDTO.getContent());
        comment.setCommentator(user.getId());
        comment.setParent_id(commentDTO.getParentID());
        comment.setType(commentDTO.getType());
        commentService.insert(comment);
        return ResultDTO.okOf();
    }
}
