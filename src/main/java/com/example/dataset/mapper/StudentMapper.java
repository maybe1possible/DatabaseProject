package com.example.dataset.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentMapper {

    @Select("select nickname from users where score>60")
    List<String> list();
}
