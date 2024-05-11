package org.Big_Event.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.Big_Event.mapper.ArticleMapper;
import org.Big_Event.pojo.Article;
import org.Big_Event.pojo.PageBean;
import org.Big_Event.pojo.Result;
import org.Big_Event.service.ArticleService;
import org.Big_Event.untils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void add(Article article) {
        //补充用户id
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        article.setCreateUser(id);
        articleMapper.add(article);
    }

    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, String categoryId, String state) {
        //1.创建PageBean对象
        PageBean<Article> pb = new PageBean<>();

        //2.开启分页查询 PageHelper插件帮助 (反正如果用到分页开发就用这个插件进行帮忙)
        PageHelper.startPage(pageNum, pageSize);

        //3.调用mapper
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");

        //为什么要进行强转成page类型？
        //page提供了方法可以获取PageHelper分页查询后，得到的总记录和当前页数据
        List<Article> as = articleMapper.list(userId,categoryId, state);
        Page<Article> p = (Page<Article>) as;

        //把数据填充到PageBean对象中
        pb.setTotal((p.getTotal()));
        pb.setItems((p.getResult()));
        return pb;
    }

    @Override
    public Article get(Integer id) {
        return articleMapper.get(id);
    }

    @Override
    public void update(Article article) {
        //补充文章分类的id、用户名
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        article.setCreateUser(userId);
        article.setUpdateTime(LocalDateTime.now());
        articleMapper.update(article);
    }

    @Override
    public void delete(Integer id) {
        articleMapper.delete(id);
    }
}
