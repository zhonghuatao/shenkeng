package com.tao.shenkeng.service;

import com.tao.shenkeng.dto.CommentViewDTO;
import com.tao.shenkeng.enums.CommentTypeEnum;
import com.tao.shenkeng.exception.CustomizeErrorCode;
import com.tao.shenkeng.exception.CustomizeException;
import com.tao.shenkeng.mapper.CommentMapper;
import com.tao.shenkeng.mapper.QuestionMapper;
import com.tao.shenkeng.mapper.UserMapper;
import com.tao.shenkeng.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class CommentService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private UserMapper userMapper;
    @Transactional
    public void insert(Comment comment) {
        if (comment.getParent_id()==null||comment.getParent_id()==0){
            throw  new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
            }
        if(comment.getType()==null|| !CommentTypeEnum.isExist(comment.getType())){
            throw  new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }
        if(comment.getType()==CommentTypeEnum.COMMENT.getType()){
            //回复评论
            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParent_id());
            if(dbComment==null){
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }else{
                commentMapper.insert(comment);
            }
        }else{
            //回复问题
            Question dbQuestion = questionMapper.selectByPrimaryKey(comment.getParent_id());
            if(dbQuestion==null){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }else{
                commentMapper.insert(comment);
                questionService.incComment(comment.getParent_id());
            }
        }
    }

    public List<CommentViewDTO> ListByQuestionId(Long parentId) {
        CommentExample example=new CommentExample();
        //查询直接回复问题的评论
        example.createCriteria().andParent_idEqualTo(parentId).andTypeEqualTo(CommentTypeEnum.QUESTION.getType());
        //排序，按照时间倒叙
        example.setOrderByClause("gmt_create desc" );
        List<Comment> comments = commentMapper.selectByExample(example);
        if(comments==null||comments.size()==0){
            return new ArrayList<>();
        }
        //通过comments拿到所有的评论者
        Set<Long> commentators=comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
        List<Long> userIds=new ArrayList<>();
        userIds.addAll(commentators);

        UserExample userExample=new UserExample();
        //通过评论者的id获取相对用户信息
        userExample.createCriteria().andIdIn(userIds);
        List<User> users = userMapper.selectByExample(userExample);

        Map<Long,User> userMap=users.stream().collect(Collectors.toMap(user->user.getId(), user->user));
        //把comments分别封装成CommentViewDTO对象添加至list
        List<CommentViewDTO> commentViewDTOS = comments.stream().map(comment -> {
            CommentViewDTO commentViewDTO = new CommentViewDTO();
            BeanUtils.copyProperties(comment,commentViewDTO);
            commentViewDTO.setUser(userMap.get(comment.getCommentator()));
            return commentViewDTO;
        }).collect(Collectors.toList());
        return commentViewDTOS;
    }
}
