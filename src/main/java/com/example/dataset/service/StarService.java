package com.example.dataset.service;

import com.example.dataset.DTO.StarDirectoryCreateDTO;
import com.example.dataset.DTO.StarMaterialDTO;
import com.example.dataset.DTO.StarPageDTO;
import com.example.dataset.utils.PageResult;

public interface StarService {
    void makeStarDictory(StarDirectoryCreateDTO starDirectoryCreateDTO);

    void postStar(StarMaterialDTO starMaterialDTO);

    PageResult getStars(StarPageDTO starPageDTO);
}
