package com.example.dataset.mapper;

import com.example.dataset.VO.AnnouncementInfoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SystemInfoMapper {

    @Select("select information_id as announcementId, title, time from system_information order by time desc limit #{limit}")
    List<AnnouncementInfoVO> getAnnouncementByLimit(Integer limit);

    @Select("select content from system_information where information_id=#{announcementId}")
    String getAnnouncementDetail(Integer announcementId);
}
