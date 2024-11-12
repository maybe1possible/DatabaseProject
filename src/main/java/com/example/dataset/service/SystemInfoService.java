package com.example.dataset.service;

import com.example.dataset.VO.AnnouncementInfoVO;

import java.util.List;

public interface SystemInfoService {
    List<AnnouncementInfoVO> getAnnouncement(Integer limit);

    String getAnnouncementDetail(Integer announcementId);
}
