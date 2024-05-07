package org.Big_Event.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.Big_Event.pojo.Result;
import org.Big_Event.untils.JwtUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @GetMapping("/list")
    public Result list() {
        return Result.success("所有文章数据");
    }

}
