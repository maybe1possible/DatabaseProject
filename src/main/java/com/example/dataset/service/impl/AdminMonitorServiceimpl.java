package com.example.dataset.service.impl;

import com.example.dataset.VO.ArticleMonitorVO;
import com.example.dataset.VO.DownloadMonitorVO;
import com.example.dataset.VO.UserMonitorVO;
import com.example.dataset.mapper.AdminMonitorMapper;
import com.example.dataset.service.AdminMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminMonitorServiceimpl implements AdminMonitorService {
    @Autowired
    private AdminMonitorMapper adminMonitorMapper;

    @Override
    public List<DownloadMonitorVO> getDownloadMonitor(String type) {
        return adminMonitorMapper.getDownloadMonitor(type);
    }

    @Override
    public List<ArticleMonitorVO> getArticleMonitor(String type) {
        return adminMonitorMapper.getArticleMonitor(type);
    }

    @Override
    public List<UserMonitorVO> getUserMonitor(String type) {
        return adminMonitorMapper.getUserMonitor(type);
    }
}
