package com.example.dataset.service.impl;

import com.example.dataset.VO.ViewHistoryVO;
import com.example.dataset.mapper.ViewHistoryMapper;
import com.example.dataset.service.ViewHistoryService;
import com.example.dataset.utils.PageResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class ViewHistoryServiceimpl implements ViewHistoryService {
    @Autowired
    ViewHistoryMapper viewHistoryMapper;

    @Override
    @Transactional
    public void addViewHistory(Integer userId, Integer articleId) {
        Integer id = viewHistoryMapper.getIdByUserAndArticleId(userId, articleId);
        if (id == null) {
            viewHistoryMapper.addViewHistory(userId, articleId, LocalDateTime.now());
        } else {
            viewHistoryMapper.updateViewHistoryTime(id, LocalDateTime.now());
        }

    }

    @Override
    public PageResult getViewHistory(Integer userId, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        Page<ViewHistoryVO> page = viewHistoryMapper.getViewHistoryPageById(userId);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public void deleteViewHistory(Integer viewHistoryId) {
        viewHistoryMapper.deleteViewHistory(viewHistoryId);
    }
}
