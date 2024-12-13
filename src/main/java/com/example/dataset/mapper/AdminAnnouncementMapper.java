package com.example.dataset.mapper;

import com.example.dataset.VO.AdminAnnouncementInfoVO;
import com.example.dataset.entity.SystemInformation;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AdminAnnouncementMapper {
    @Insert("insert into system_information (admin_id, content, time, title) " +
            "values " +
            "(#{adminId}, #{content}, #{time}, #{title})")
    void publish(SystemInformation systemInformation);

    @Delete("delete from system_information where information_id=#{id}")
    void delete(Integer id);

    @Update("update system_information set admin_id=#{adminId}, content=#{content}, time=#{time}, title=#{title} where information_id=#{informationId}")
    void set(SystemInformation systemInformation);

    @Select("select information_id as id, title, content, time from system_information order by time")
    List<AdminAnnouncementInfoVO> getAllAnnouncement();
}
