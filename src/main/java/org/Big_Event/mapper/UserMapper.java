package org.Big_Event.mapper;

import org.Big_Event.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author GranTHdxY
 */
@Mapper
public interface UserMapper {
    //根据用户名查询用户
    @Select("select * from user where username = #{username}")
    User findByUserName(String username);

    @Insert("insert into user(username, password, create_time, update_time)" +
            " values(#{username}, #{password}, now(), now())")
    //添加到数据库
    void add(String username, String password);

    //更新用户信息
    @Update("update user set nickname = #{nickname}, email = #{email}, update_time = #{updateTime} where id = #{id}")
    void update(User user);

    //更新用户头像
    @Update("update user set user_pic = #{url}, update_time = now() where id = #{id}")
    void updateAvatar(String url, Integer id);

    //更新用户密码
    @Update("update user set password = #{newPwd_MD5}, update_time = now() where id = #{id}")
    void updatePwd(String newPwd_MD5, Integer id);
}
