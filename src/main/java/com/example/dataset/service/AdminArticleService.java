package com.example.dataset.service;

import com.example.dataset.DTO.SetArticleStatusDTO;
import com.example.dataset.utils.PageResult;

public interface AdminArticleService {
    PageResult getArticleWithoutAudit(Integer pageNum, Integer pageSize);

    void setArticleStatus(SetArticleStatusDTO setArticleStatusDTO);
}
