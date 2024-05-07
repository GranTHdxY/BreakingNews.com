package org.Big_Event.service.impl;

import org.Big_Event.mapper.UserMapper;
import org.Big_Event.pojo.User;
import org.Big_Event.service.UserService;
import org.Big_Event.untils.Md5Util;
import org.Big_Event.untils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUserName(String username) {
        User u = userMapper.findByUserName(username);
        return u;
    }

    @Override
    public void register(String username, String password) {
        //加密（用md5对密码加密）
        //返回一个密文字符串
        String md5String = Md5Util.getMD5String(password);
        userMapper.add(username, md5String);
    }

    @Override
    public void update(User user){
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }

    @Override
    public void updateAvatar(String url){
        //从ThreadLocal中获取用户id
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updateAvatar(url, id);
    }

    @Override
    public void updatePwd(String newPwd){
        //从ThreadLocal中获取用户密码
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updatePwd(Md5Util.getMD5String(newPwd),id);
    }


}
