package com.example.dataset.service;

import com.example.dataset.DTO.StarDirectoryCreateDTO;
import com.example.dataset.DTO.StarMaterialDTO;
import com.example.dataset.utils.PageResult;

public interface StarService {
    void makeStarDictory(StarDirectoryCreateDTO starDirectoryCreateDTO);

    void postStar(StarMaterialDTO starMaterialDTO);

    PageResult getStars(Integer favorites_id, Integer user_id, Integer pageNumber, Integer pageSize);
}
