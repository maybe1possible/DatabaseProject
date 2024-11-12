package com.example.dataset.service;

import com.example.dataset.VO.SearchHistoryVO;

import java.util.List;

public interface SearchHistoryService {
    void addSearchHistory(int userId, String keyword);

    List<SearchHistoryVO> getSearchHistory(int userId);
}
