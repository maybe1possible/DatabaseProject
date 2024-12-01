package com.example.dataset.service;

import com.example.dataset.DTO.SetCommentStatusDTO;
import com.example.dataset.utils.PageResult;

public interface AdminCommentService {
    PageResult getCommentWithoutAudit(Integer pageNum, Integer pageSize);

    void setCommentStatus(SetCommentStatusDTO setCommentStatusDTO);
}
