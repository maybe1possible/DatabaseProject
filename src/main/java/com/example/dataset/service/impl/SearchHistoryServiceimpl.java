package com.example.dataset.service.impl;

import com.example.dataset.VO.SearchHistoryVO;
import com.example.dataset.mapper.SearchHistoryMapper;
import com.example.dataset.service.SearchHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SearchHistoryServiceimpl implements SearchHistoryService {

    @Autowired
    private SearchHistoryMapper searchHistoryMapper;

    @Override
    public void addSearchHistory(int userId, String keyword) {
        searchHistoryMapper.addSearchHistory(userId, keyword, LocalDateTime.now());
    }

    @Override
    public List<SearchHistoryVO> getSearchHistory(int userId) {
        return searchHistoryMapper.getSearchHistoryByUserId(userId);
    }
}
