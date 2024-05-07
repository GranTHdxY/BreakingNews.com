package org.Big_Event.service;

import org.Big_Event.pojo.User;
import org.apache.ibatis.annotations.Select;



public interface UserService {
    //注册
    void register(String username, String password);

    //根据用户名查询用户
    User findByUserName(String username);

    void update(User user);

    void updateAvatar(String avatarUrl);

    void updatePwd(String newPwd);
}
