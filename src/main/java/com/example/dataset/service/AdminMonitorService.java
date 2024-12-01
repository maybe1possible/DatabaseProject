package com.example.dataset.service;

import com.example.dataset.VO.ArticleMonitorVO;
import com.example.dataset.VO.DownloadMonitorVO;
import com.example.dataset.VO.UserMonitorVO;

import java.util.List;

public interface AdminMonitorService {

    List<DownloadMonitorVO> getDownloadMonitor(String type);

    List<ArticleMonitorVO> getArticleMonitor(String type);

    List<UserMonitorVO> getUserMonitor(String type);
}
