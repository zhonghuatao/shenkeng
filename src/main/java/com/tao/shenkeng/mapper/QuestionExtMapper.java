package com.tao.shenkeng.mapper;

import com.tao.shenkeng.model.Question;
import org.springframework.stereotype.Component;

@Component
public interface QuestionExtMapper {
    int incView(Question recode);

    int incComment(Question recode);
}
