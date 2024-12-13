package com.example.dataset.service.impl;

import ch.qos.logback.core.util.SystemInfo;
import com.example.dataset.DTO.AnnouncementPublishDTO;
import com.example.dataset.DTO.AnnouncementSetDTO;
import com.example.dataset.VO.AdminAnnouncementInfoVO;
import com.example.dataset.entity.SystemInformation;
import com.example.dataset.mapper.AdminAnnouncementMapper;
import com.example.dataset.service.AdminAnnouncementService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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

    @Override
    public void delete(Integer id) {
        adminAnnouncementMapper.delete(id);
    }

    @Override
    public void set(AnnouncementSetDTO announcementSetDTO) {
        SystemInformation systemInformation = new SystemInformation();
        BeanUtils.copyProperties(announcementSetDTO, systemInformation);
        systemInformation.setTime(LocalDateTime.now());
        adminAnnouncementMapper.set(systemInformation);
    }

    @Override
    public List<AdminAnnouncementInfoVO> getAllAnnouncement() {
        return adminAnnouncementMapper.getAllAnnouncement();
    }
}
