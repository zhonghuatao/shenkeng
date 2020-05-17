package com.tao.shenkeng.service;

import com.tao.shenkeng.mapper.UserMapper;
import com.tao.shenkeng.model.User;
import com.tao.shenkeng.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void createOrUpDate(User user) {
        UserExample userExample=new UserExample();
        userExample.createCriteria().andAccount_idEqualTo(user.getAccount_id());
        List<User> users=userMapper.selectByExample(userExample);
        if(users.size()==0){
            //用户不存在插入用户数据
            user.setGmt_create(System.currentTimeMillis());
            user.setGmt_modified(user.getGmt_create());
            userMapper.insert(user);
        }else{
            //用户存在更新
            User updateUser = new User();
            updateUser.setName(user.getName());
            updateUser.setAvatar_url(user.getAvatar_url());
            updateUser.setToken(user.getToken());
            updateUser.setAccount_id(user.getAccount_id());
            userMapper.updateByExampleSelective(updateUser,userExample);
        }
    }
}
