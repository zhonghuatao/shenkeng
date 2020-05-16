package com.tao.shenkeng.mapper;

import com.tao.shenkeng.model.Question;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface QuestionMapper {

    //文章信息插入数据库
    @Insert("insert into question(" +
            "title,description,gmt_create,gmt_modified,creator,tag)" +
            "values(#{title},#{description},#{gmt_create},#{gmt_modified},#{creator},#{tag})")
    void create(Question question);
    //分页查询，offset表示数据下标，size表示要查询的数据条数
    @Select("select * from question limit #{offset},#{size}")
    List<Question> list(@Param("offset") Integer offset, @Param("size") Integer size);

    @Select("select count(1) from question")
    Integer count();

    @Select("select * from question where creator=#{id} limit #{offset},#{size}")
    List<Question> userById(Integer id, Integer offset, Integer size);

    @Select("select count(1) from question where creator=#{id} ")
    Integer countCreator(Integer id);

    @Select("select * from question where id=#{id}")
    Question getById(Integer id);
    @Update("update question set title=#{title},description=#{description},gmt_modified=#{gmt_modified},tag=#{tag} where id=#{id}")
    void update(Question question);
}
