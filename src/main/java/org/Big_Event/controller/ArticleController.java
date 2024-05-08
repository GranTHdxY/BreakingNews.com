package org.Big_Event.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.Big_Event.pojo.Article;
import org.Big_Event.pojo.PageBean;
import org.Big_Event.pojo.Result;
import org.Big_Event.service.ArticleService;
import org.Big_Event.untils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    ArticleService articleService;

    //添加文章
    @PostMapping
    public Result add(@RequestBody @Validated Article article){
        articleService.add(article);
        return Result.success();
    }

    //获取文章详情
    //这里的参数required 是 必须 非必须的意思
    @GetMapping
    public Result<PageBean<Article>> list(Integer pageNum, Integer pageSize,
                                          @RequestParam(required = false) String categoryId,
                                          @RequestParam(required = false) String state){
        PageBean<Article> pb = articleService.list(pageNum, pageSize, categoryId, state);
        return Result.success(pb);

    }

}
