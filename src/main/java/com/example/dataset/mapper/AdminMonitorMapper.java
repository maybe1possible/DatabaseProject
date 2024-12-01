package com.example.dataset.mapper;

import com.example.dataset.VO.ArticleMonitorVO;
import com.example.dataset.VO.DownloadMonitorVO;
import com.example.dataset.VO.UserMonitorVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMonitorMapper {
    List<DownloadMonitorVO> getDownloadMonitor(String type);

    List<ArticleMonitorVO> getArticleMonitor(String type);

    List<UserMonitorVO> getUserMonitor(String type);
}
