package com.tao.shenkeng.dto;

import lombok.Data;
import org.springframework.stereotype.Component;
@Data
@Component
public class GithubUser {
    private String name;
    private String bio;
    private Long id;
    private String avatar_url;
}
