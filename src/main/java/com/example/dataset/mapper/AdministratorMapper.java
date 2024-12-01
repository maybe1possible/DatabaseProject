package com.example.dataset.mapper;

import com.example.dataset.entity.Administrator;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdministratorMapper {

    @Select("select * from administrators where username=#{username}")
    Administrator getByUsername(String username);

    @Insert("insert into administrators (username, password, name, last_operate_time) values " +
            "(#{username}, #{password}, #{name}, #{lastOperateTime})")
    void add(Administrator administrator);
}
