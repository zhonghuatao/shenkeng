package com.tao.shenkeng.provider;

import com.alibaba.fastjson.JSON;
import com.tao.shenkeng.dto.AccessTokenDTO;
import com.tao.shenkeng.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;
/*
* 配置Github oAuth
* */
@Component
public class GithubProvider {
    //通过code来获取accessToken
    public String getAccessToken(AccessTokenDTO accessTokenDTO){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string= response.body().string();
            String token = string.split("&")[0].split("=")[1];
            System.out.println(token);
            return token;
        } catch (Exception e) {
           e.printStackTrace();
        }
        return null;
    }

    //通过发回accessToken来获取用户信息
    public GithubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://api.github.com/user?access_token="+accessToken)
                    .build();
        try {
            Response  response = client.newCall(request).execute();
            String string= response.body().string();
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            return githubUser;
        } catch (Exception e) {
            e.printStackTrace();
        }
            return null;
    }
}
