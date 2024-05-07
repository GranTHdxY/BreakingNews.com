package org.Big_Event.controller;

import org.Big_Event.pojo.Category;
import org.Big_Event.pojo.Result;
import org.Big_Event.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@Validated
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    //新增文章分类
    //没有进行检查文章是否存在？？ 优化？
    @PostMapping
    public Result add(@RequestBody @Validated Category category){
        categoryService.add(category);
        return Result.success();
    }

    //获取当前已登录用户创建的所有文章分类
    @GetMapping
    public Result<List<Category>> list(){
        List<Category> cl = categoryService.list();
        return Result.success(cl);
    }





}
