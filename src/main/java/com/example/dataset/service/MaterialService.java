package com.example.dataset.service;

import com.example.dataset.DTO.MaterialInfoDTO;
import com.example.dataset.DTO.MaterialPageDTO;
import com.example.dataset.DTO.MyMaterialPageDTO;
import com.example.dataset.entity.Material;
import com.example.dataset.utils.PageResult;

public interface MaterialService {
    void upload(Material material, String[] tags);

    String download(int materialId);

    PageResult pageSearchByKeyword(MaterialPageDTO materialPageDTO);

    PageResult pageSearchByNav(MaterialPageDTO materialPageDTO);

    PageResult pageSearchById(MyMaterialPageDTO myMaterialPageDTO);

    MaterialInfoDTO getMaterialById(int materialId);
}
