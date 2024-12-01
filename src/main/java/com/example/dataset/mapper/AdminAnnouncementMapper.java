package com.example.dataset.mapper;

import com.example.dataset.entity.SystemInformation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminAnnouncementMapper {
    @Insert("insert into system_information (admin_id, content, time, title) " +
            "values " +
            "(#{adminId}, #{content}, #{time}, #{title})")
    void publish(SystemInformation systemInformation);
}
