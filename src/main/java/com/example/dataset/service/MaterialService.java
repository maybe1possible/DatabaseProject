package com.example.dataset.service;

import com.example.dataset.DTO.MaterialInfoDTO;
import com.example.dataset.DTO.MaterialPageDTO;
import com.example.dataset.entity.Material;
import com.example.dataset.utils.PageResult;

public interface MaterialService {
    void upload(Material material, String[] tags);

    String download(int materialId);

    PageResult pageSearchByKeyword(MaterialPageDTO materialPageDTO);

    PageResult pageSearchByNav(MaterialPageDTO materialPageDTO);

    PageResult pageSearchById(int userId, Integer page, Integer pageSize, String sort);

    MaterialInfoDTO getMaterialById(int materialId);
}
