package com.tao.shenkeng.mapper;

import com.tao.shenkeng.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
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

    @Select("select * from question")
    List<Question> list();
}
