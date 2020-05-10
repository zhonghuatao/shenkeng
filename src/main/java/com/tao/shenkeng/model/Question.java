package com.tao.shenkeng.model;

import lombok.Data;
import org.springframework.stereotype.Component;



@Component
@Data
public class Question {

    private String title;
    private  String description;
    private Long gmt_create;
    private Long gmt_modified;
    private int creator;
    private int comment_count;
    private int view_count;
    private int like_count;
    private String tag;

}
