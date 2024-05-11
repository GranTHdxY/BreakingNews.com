package org.Big_Event.mapper;

import org.Big_Event.pojo.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author GranTHdxY
 */
@Mapper
public interface ArticleMapper {

    @Insert("insert into article(title, content, cover_img, state, category_id, create_user, create_time, update_time)" +
            " values(#{title}, #{content}, #{coverImg}, #{state}, #{categoryId}, #{createUser}, now(), now())")
    void add(Article article);

    //这里需要动态sql 用注解写动态sql会非常麻烦
    //用映射配置文件写
    List<Article> list(Integer userId, String categoryId, String state);

    //根据文章ID查询文章
    @Select("select * from article where id = #{id}")
    Article get(Integer id);

    //更新文章
    @Update("update article set title = #{title}, content =#{content}, cover_img = #{coverImg}, category_id = #{categoryId}, state = #{state} where id = #{id}")
    void update(Article article);


    @Delete("delete from article where id = #{id}")
    void delete(Integer id);
}
