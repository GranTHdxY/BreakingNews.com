package org.Big_Event.config;

import org.Big_Event.intercepetors.LoginIntercepetors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//注册拦截器
@Configuration
public class WebConfig implements WebMvcConfigurer {

    //拦截器对象
    @Autowired
    private LoginIntercepetors loginIntercepetors;


    @Override
    public void addInterceptors(InterceptorRegistry registry){
        //不拦截登录和注册接口
        registry.addInterceptor(loginIntercepetors).excludePathPatterns("/user/login","/user/register");
    }

}
