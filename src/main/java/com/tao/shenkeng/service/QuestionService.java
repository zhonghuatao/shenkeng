package com.tao.shenkeng.service;

import com.tao.shenkeng.dto.PaginationDTO;
import com.tao.shenkeng.dto.QuestionDTO;
import com.tao.shenkeng.exception.CustomizeErrorCode;
import com.tao.shenkeng.exception.CustomizeException;
import com.tao.shenkeng.mapper.QuestionExtMapper;
import com.tao.shenkeng.mapper.QuestionMapper;
import com.tao.shenkeng.mapper.UserMapper;
import com.tao.shenkeng.model.Question;
import com.tao.shenkeng.model.QuestionExample;
import com.tao.shenkeng.model.User;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionExtMapper questionExtMapper;
    //user数据表和question表连接
    public PaginationDTO list(Integer page, Integer size) {
        //分页数据
        QuestionExample example = new QuestionExample();
        Integer totalCount = (int)questionMapper.countByExample(example);
        example.setOrderByClause("gmt_create desc" );
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer offset=size*(page-1);
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(example,new RowBounds(offset,size));
        List<QuestionDTO> questionDTOList=new ArrayList<>();

        for (Question question:questions) {
          User user=  userMapper.selectByPrimaryKey(question.getCreator());
          QuestionDTO questionDTO=new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        paginationDTO.setPagination(totalCount,page,size);
        return paginationDTO;
    }

    public PaginationDTO list(Long id, Integer page, Integer size) {
        QuestionExample example = new QuestionExample();
        example.createCriteria().andCreatorEqualTo(id);
        Integer totalCount =(int)(questionMapper.countByExample(example));
        example.setOrderByClause("gmt_create desc" );
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer offset=size*(page-1);
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(example,new RowBounds(offset,size));
        List<QuestionDTO> questionDTOList=new ArrayList<>();

        for (Question question:questions) {
            User user=  userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO=new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        paginationDTO.setPagination(totalCount,page,size);
        return paginationDTO;
    }

    public QuestionDTO getById(Long id) {
        Question question=questionMapper.selectByPrimaryKey(id);
        if (question==null){
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        QuestionDTO questionDTO=new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        User user=  userMapper.selectByPrimaryKey(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpDate(Question question) {
        if (question.getId()==null){
            //创建问题信息
            question.setGmt_create(System.currentTimeMillis());
            question.setGmt_modified(question.getGmt_create());
            question.setComment_count((long) 0);
            question.setLike_count((long) 0);
            question.setView_count((long) 0);
            questionMapper.insert(question);
        }else{
            //更新
            Question questions = new Question();
            questions.setGmt_modified(System.currentTimeMillis());
            questions.setTitle(question.getTitle());
            questions.setTag(question.getTag());
            questions.setDescription(question.getDescription());
            QuestionExample example = new QuestionExample();
            example.createCriteria().andIdEqualTo(question.getId());
            int updated = questionMapper.updateByExampleSelective(questions, example);
            if (updated!=1){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }
    //浏览数,使用了自定义的incView方法进行数据更新
    public void incView(Long id) {
        Question question = new Question();
        question.setId(id);
        question.setView_count((long) 1);
        questionExtMapper.incView(question);
    }
    //评论数
    public void incComment(Long id) {
        Question question = new Question();
        question.setId(id);
        question.setComment_count((long) 1);
        questionExtMapper.incComment(question);
    }

    //文章热度列表
    public PaginationDTO MaxView() {
        PaginationDTO paginationDTO = new PaginationDTO();
        QuestionExample example=new QuestionExample();
        List<Question> questions = questionMapper.selectByExample(example);
        List<Long> viewList=new ArrayList<>();
        for (Question question:questions) {
            Long view_count = question.getView_count();
            viewList.add(view_count);
        }
        List<QuestionDTO> questionDTOView=new ArrayList<>();
        Collections.sort(viewList);
        int count=0;
        for (int i = viewList.size()-1; i >viewList.size()-6 ; i--) {
            if (count==5){
                break;
            }else{
                QuestionExample viewExample=new QuestionExample();
                viewExample.createCriteria().andView_countEqualTo(viewList.get(i));
                List<Question> questions1 = questionMapper.selectByExample(viewExample);
                for (Question question:questions1) {
                    QuestionDTO questionDTO=new QuestionDTO();
                    BeanUtils.copyProperties(question,questionDTO);
                    questionDTOView.add(questionDTO);
                    count++;
                    if (count==5){
                        break;
                    }
                }
            }
        }
        paginationDTO.setQuestions(questionDTOView);
        return paginationDTO;
    }
}
