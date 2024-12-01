package com.example.dataset.mapper;

import com.example.dataset.VO.AdminUserVO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface AdminUserMapper {
    @Select("select user_id as id, nickname, status from users order by gen_time")
    Page<AdminUserVO> getAllUser();

    @Select("select user_id as id, nickname, status from users where user_id=#{id}")
    AdminUserVO getUserById(Integer id);

    @Update("update users set status=#{status} where user_id=#{id}")
    void setStatus(Integer id, Integer status);
}
