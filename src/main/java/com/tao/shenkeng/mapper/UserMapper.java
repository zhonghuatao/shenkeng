package com.tao.shenkeng.mapper;

import com.tao.shenkeng.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    @Insert("insert into user(account_id,name,token,gmt_create,gmt_modified)values(#{account_id},#{name},#{token},#{gmt_create},#{gmt_modified})")
    void insert(User user);
}
