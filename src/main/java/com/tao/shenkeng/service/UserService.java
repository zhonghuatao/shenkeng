package com.tao.shenkeng.service;

import com.tao.shenkeng.mapper.UserMapper;
import com.tao.shenkeng.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void createOrUpDate(User user) {
        User dbUser=userMapper.findByAccountID(user.getAccount_id());
        if(dbUser==null){
            //用户不存在插入用户数据
            user.setGmt_create(System.currentTimeMillis());
            user.setGmt_modified(user.getGmt_create());
            userMapper.insert(user);
        }else{
            //用户存在更新
            dbUser.setToken(user.getToken());
            dbUser.setAccount_id(user.getAccount_id());
            dbUser.setName(user.getName());
            dbUser.setAvatar_url(user.getAvatar_url());
            userMapper.update(dbUser);
        }
    }
}
