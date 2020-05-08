package com.tao.shenkeng.controller;

import com.tao.shenkeng.dto.AccessTokenDTO;
import com.tao.shenkeng.dto.GithubUser;
import com.tao.shenkeng.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String ClientID;
    @Value("${github.client.secret}")
    private String ClientSecret;
    @Value("${github.redirect.uri}")
    private String RedirectUri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code")String code,
                           @RequestParam(name = "state")String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(ClientID);
        accessTokenDTO.setClient_secret(ClientSecret);
        //重定向url
        accessTokenDTO.setRedirect_uri(RedirectUri);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser User = githubProvider.getGithubUser(accessToken);
        System.out.println(User.getName());
        return "index";
    }
}
