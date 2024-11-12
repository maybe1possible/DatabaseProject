package com.example.dataset.service.impl;

import com.example.dataset.VO.AnnouncementInfoVO;
import com.example.dataset.mapper.SystemInfoMapper;
import com.example.dataset.service.SystemInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemInfoServiceimpl implements SystemInfoService {

    @Autowired
    SystemInfoMapper systemInfoMapper;

    @Override
    public List<AnnouncementInfoVO> getAnnouncement(Integer limit) {
        return systemInfoMapper.getAnnouncementByLimit(limit);
    }

    @Override
    public String getAnnouncementDetail(Integer announcementId) {
        return systemInfoMapper.getAnnouncementDetail(announcementId);
    }
}
