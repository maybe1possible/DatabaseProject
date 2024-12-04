package com.example.dataset.service;

import com.example.dataset.VO.SearchHistoryVO;

import java.util.List;

public interface SearchHistoryService {
    void addSearchHistory(Integer userId, String keyword);

    List<SearchHistoryVO> getSearchHistory(Integer userId);

    void deleteSearchHistory(Integer searchHistoryId);
}
