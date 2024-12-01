package com.example.dataset.service.impl;

import ch.qos.logback.core.util.SystemInfo;
import com.example.dataset.DTO.AnnouncementPublishDTO;
import com.example.dataset.entity.SystemInformation;
import com.example.dataset.mapper.AdminAnnouncementMapper;
import com.example.dataset.service.AdminAnnouncementService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AdminAnnouncementServiceimpl implements AdminAnnouncementService {
    @Autowired
    AdminAnnouncementMapper adminAnnouncementMapper;

    @Override
    public void publish(AnnouncementPublishDTO announcementPublishDTO) {
        SystemInformation systemInformation = new SystemInformation();
        BeanUtils.copyProperties(announcementPublishDTO, systemInformation);
        systemInformation.setTime(LocalDateTime.now());
        adminAnnouncementMapper.publish(systemInformation);
    }
}
