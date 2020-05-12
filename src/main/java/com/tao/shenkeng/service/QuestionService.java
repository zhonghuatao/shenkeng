package com.tao.shenkeng.service;

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
    public List<QuestionDTO> list() {
        List<Question> questions = questionMapper.list();
        List<QuestionDTO> questionDTOList=new ArrayList<>();
        for (Question question:questions) {
          User user=  userMapper.findById(question.getCreator());
          QuestionDTO questionDTO=new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }
}
