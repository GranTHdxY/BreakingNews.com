package org.Big_Event.controller;

import jakarta.validation.constraints.Pattern;
import org.Big_Event.pojo.Result;
import org.Big_Event.pojo.User;
import org.Big_Event.service.UserService;
import org.Big_Event.untils.JwtUtil;
import org.Big_Event.untils.Md5Util;
import org.Big_Event.untils.ThreadLocalUtil;
import org.apache.catalina.util.StringUtil;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$")String username, @Pattern(regexp = "^\\S{5,16}$") String password){
        //查询用户
        User u = userService.findByUserName(username);
        if (u == null){
            //注册
            userService.register(username, password);
            return Result.success();
        } else {
            //如果用户名已经被占用了
            return Result.error("用户名已被占用");
        }
    }

    //改进：应该写在Service层？
    //遇到问题：为什么
    @PostMapping("/login")
    public Result login(@Pattern(regexp = "^\\S{5,16}$")String username, @Pattern(regexp = "^\\S{5,16}$") String password){
        //查询用户
        User loginUser = userService.findByUserName(username);
        if(loginUser != null){
            //密码是否正确
            if(Md5Util.getMD5String(password).equals(loginUser.getPassword())){
                Map<String, Object> claims = new HashMap<>();
                claims.put("id",loginUser.getId());
                claims.put("username",loginUser.getUsername());
                String token = JwtUtil.genToken(claims);
                //密码正确
                return Result.success(token);
            }else{
                return Result.error("密码错误");
            }

        }else{
            //用户不存在
            return Result.error("用户名错误、");
        }
    }

    @GetMapping("/userInfo")
    public Result userInfo(@RequestHeader(name = "Authorization") String token){
        //根据用户名查询用户
        //在token串中进行获取信息
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User u = userService.findByUserName(username);
        return Result.success(u);
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user){
        userService.update(user);
        return Result.success();
    }

    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl){
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }

    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String, String> params) {
        //参数校验
        String old_pwd = params.get("old_pwd");
        String new_pwd = params.get("new_pwd");
        String re_pwd  = params.get("re_pwd");
        if (!StringUtils.hasLength(old_pwd) || !StringUtils.hasLength(new_pwd) || !StringUtils.hasLength(re_pwd) ){
            return Result.error("密码不能为空");
        }

        //取出密码 判断取出的密码的md5码和old_pwd是否相同
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User loginUser = userService.findByUserName(username);
        //String password_MD5 = (String) map.get("password");
        //这里不能直接取 token串里不存密码

        String password_MD5 = loginUser.getPassword();


        if (!password_MD5.equals(Md5Util.getMD5String(old_pwd))) {
            //如果不同
            return Result.error("原密码不正确");
        }
         if (!new_pwd.equals(re_pwd)) {
            return Result.error("两次填写的密码不正确");
        }

        //如果相同 调用service更新密码
         userService.updatePwd(new_pwd);
         return Result.success();

    }
}
