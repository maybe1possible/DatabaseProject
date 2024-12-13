package com.example.dataset.service;

import com.example.dataset.DTO.AnnouncementPublishDTO;
import com.example.dataset.DTO.AnnouncementSetDTO;
import com.example.dataset.VO.AdminAnnouncementInfoVO;

import java.util.List;

public interface AdminAnnouncementService {
    void publish(AnnouncementPublishDTO announcementPublishDTO);

    void delete(Integer id);

    void set(AnnouncementSetDTO announcementSetDTO);

    List<AdminAnnouncementInfoVO> getAllAnnouncement();
}
