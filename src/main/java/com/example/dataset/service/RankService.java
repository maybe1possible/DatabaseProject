package com.example.dataset.service;

import com.example.dataset.DTO.RankGetDTO;

import java.util.List;

public interface RankService {
    List getRankByType(RankGetDTO rankGetDTO);

}
