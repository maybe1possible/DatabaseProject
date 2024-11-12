package com.example.dataset.service;

import com.example.dataset.utils.PageResult;

public interface ViewHistoryService {
    void addViewHistory(int userId, int articleId);

    PageResult getViewHistory(int userId, int pageSize, int pageNum);
}
