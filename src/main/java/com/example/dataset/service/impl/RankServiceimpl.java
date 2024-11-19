package com.example.dataset.service.impl;

import com.example.dataset.DTO.RankGetDTO;
import com.example.dataset.mapper.RankMapper;
import com.example.dataset.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankServiceimpl implements RankService {

    @Autowired
    private RankMapper rankMapper;

    @Override
    public List getRankByType(String type, int limit) {
        if (type.equals("article")) {
            return rankMapper.getArticleRank(limit);
        } else if (type.equals("user")) {
            return rankMapper.getUserRank(limit);
        } else {
            throw new RuntimeException("参数错误");
        }
    }
}
