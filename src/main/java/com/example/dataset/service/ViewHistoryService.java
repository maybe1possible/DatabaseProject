package com.example.dataset.service;

import com.example.dataset.utils.PageResult;

public interface ViewHistoryService {
    void addViewHistory(Integer userId, Integer articleId);

    PageResult getViewHistory(Integer userId, Integer pageSize, Integer pageNum);
}
