package com.tao.shenkeng.mapper;

import com.tao.shenkeng.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserMapper {
    //把用户信息插入数据库
    @Insert("insert into user(account_id,name,token,gmt_create,gmt_modified,avatar_url)values(#{account_id},#{name},#{token},#{gmt_create},#{gmt_modified},#{avatar_url})")
    void insert(User user);
    //用token查询用户的信息
    @Select("select * from user where token=#{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from user where id=#{id}")
    User findById(@Param("id")int id);
    @Select("select * from user where account_id=#{account_id}")
    User findByAccountID(@Param("account_id")String account_id);
    @Update("update user set name=#{name},token=#{token},account_id=#{account_id},avatar_url=#{avatar_url} where id=#{id}")
    void update(User user);
}
