package com.tao.shenkeng.dto;

import com.tao.shenkeng.model.User;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class QuestionDTO {
    private  Integer id;
    private String title;
    private  String description;
    private Long gmt_create;
    private Long gmt_modified;
    private Integer creator;
    private Integer comment_count;
    private Integer view_count;
    private Integer like_count;
    private String tag;
    private User user;
}
