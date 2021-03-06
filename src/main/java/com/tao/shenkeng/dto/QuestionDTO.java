package com.tao.shenkeng.dto;

import com.tao.shenkeng.model.User;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class QuestionDTO {
    private  Long id;
    private String title;
    private  String description;
    private Long gmt_create;
    private Long gmt_modified;
    private Long creator;
    private Long comment_count;
    private Long view_count;
    private Long like_count;
    private String tag;
    private User user;

}
