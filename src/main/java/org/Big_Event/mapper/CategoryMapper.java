package org.Big_Event.mapper;

import org.Big_Event.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper

public interface CategoryMapper {

    //新增分类
    @Insert("insert into category(category_name, category_alias,create_user, create_time, update_time)" +
            " values(#{categoryName}, #{categoryAlias}, #{createUser}, #{createTime}, #{updateTime})")
    void add(Category category);


    //查询所有
    @Select("select * from category where create_user = #{userId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "categoryName", column = "category_name"),
            @Result(property = "categoryAlias", column = "category_alias"),
            @Result(property = "categoryUser", column = "category_user"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time")
    })
    List<Category> list(Integer userId);

    @Select("select * from category where id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "categoryName", column = "category_name"),
            @Result(property = "categoryAlias", column = "category_alias"),
            @Result(property = "categoryUser", column = "category_user"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time")
    })
    Category findCategoryById(Integer id);

    @Update("update category set category_name = #{categoryName}, category_alias = #{categoryAlias}," +
            "update_time = now() where id = #{id}")
    void update(Category category);

    @Delete("delete from category WHERE id = #{id}")
    void delete(Category category);
}
