package org.Big_Event.service;

import org.Big_Event.pojo.Article;
import org.Big_Event.pojo.PageBean;

public interface ArticleService {
    //添加文章
    void add(Article article);

    //条件分页文章列表查询
    PageBean<Article> list(Integer pageNum, Integer pageSize, String categoryId, String state);

    //根据文章id获取文章详情
    Article get(Integer id);

    //更新文章信息
    void update(Article article);

    void delete(Integer id);
}
