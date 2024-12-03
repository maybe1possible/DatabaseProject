package com.example.dataset.service;

import com.example.dataset.DTO.ChangeCompetenceDTO;
import com.example.dataset.DTO.DeleteArticleDTO;
import com.example.dataset.DTO.MaterialInfoDTO;
import com.example.dataset.DTO.MaterialPageDTO;
import com.example.dataset.entity.Material;
import com.example.dataset.utils.PageResult;

public interface MaterialService {
    void upload(Material material, String[] tags);

    String download(Integer materialId);

    PageResult pageSearchByKeyword(MaterialPageDTO materialPageDTO);

    PageResult pageSearchByNav(MaterialPageDTO materialPageDTO);

    PageResult pageSearchById(Integer userId, Integer page, Integer pageSize, String sort);

    MaterialInfoDTO getMaterialById(Integer materialId);

    void deleteArticle(DeleteArticleDTO deleteArticleDTO);

    void changeCompetence(ChangeCompetenceDTO changeCompetenceDTO);
}
