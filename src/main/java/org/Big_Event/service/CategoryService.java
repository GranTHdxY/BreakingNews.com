package org.Big_Event.service;

import org.Big_Event.pojo.Category;

import java.util.List;

public interface CategoryService {
    //新增文章分类
    void add(Category category);

    //文章分类列表查询
    List<Category> list();
}
