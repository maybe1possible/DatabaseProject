package com.example.dataset.mapper;

import com.example.dataset.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;

@Mapper
public interface UserMapper {

    @Select("select * from users where openid = #{openid}")
    User getByOpenid(String openid);

    void insert(User user);

    @Select("select * from users where user_id = #{userId}")
    User getById(int userId);

    void update(User user);

    @Update("update users set last_login_time = #{now} where user_id = #{userId}")
    void setLastLoginTime(int userId, LocalDateTime now);
}
