package com.tao.shenkeng.service;

import com.tao.shenkeng.dto.PaginationDTO;
import com.tao.shenkeng.dto.QuestionDTO;
import com.tao.shenkeng.mapper.QuestionMapper;
import com.tao.shenkeng.mapper.UserMapper;
import com.tao.shenkeng.model.Question;
import com.tao.shenkeng.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;
    //user数据表和question表连接
    public PaginationDTO list(Integer page, Integer size) {
        //分页数据
        Integer totalCount = questionMapper.count();
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer offset=size*(page-1);
        List<Question> questions = questionMapper.list(offset,size);
        List<QuestionDTO> questionDTOList=new ArrayList<>();

        for (Question question:questions) {
          User user=  userMapper.findById(question.getCreator());
          QuestionDTO questionDTO=new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        paginationDTO.setPagination(totalCount,page,size);
        return paginationDTO;
    }

    public PaginationDTO list(Integer id, Integer page, Integer size) {
        Integer totalCount = questionMapper.countCreator(id);
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer offset=size*(page-1);
        List<Question> questions = questionMapper.userById(id,offset,size);
        List<QuestionDTO> questionDTOList=new ArrayList<>();

        for (Question question:questions) {
            User user=  userMapper.findById(question.getCreator());
            QuestionDTO questionDTO=new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        paginationDTO.setPagination(totalCount,page,size);
        return paginationDTO;
    }

    public QuestionDTO getById(Integer id) {
        Question question=questionMapper.getById(id);
        QuestionDTO questionDTO=new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        User user=  userMapper.findById(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpDate(Question question) {
        if (question.getId()==null){
            //创建问题信息
            question.setGmt_create(System.currentTimeMillis());
            question.setGmt_modified(question.getGmt_create());
            questionMapper.create(question);
        }else{
            //更新
            question.setGmt_modified(System.currentTimeMillis());
            questionMapper.update(question);
        }
    }
}
