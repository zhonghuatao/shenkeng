package com.tao.shenkeng.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
//回复评论时从前端接收ajax数据
public class CommentDTO {
    private Long parentID;
    private String  content;
    private Integer type;
}
