package com.tao.shenkeng.dto;

import com.tao.shenkeng.model.User;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class CommentViewDTO {
    private Long id;
    private Long parent_id;
    private int type;
    private Long commentator;
    private Long gmt_create;
    private Long gmt_modified;
    private Long like_count;
    private String content;
    private User user;
}
