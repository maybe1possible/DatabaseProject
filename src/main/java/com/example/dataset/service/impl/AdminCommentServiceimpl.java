package com.example.dataset.service.impl;

import com.example.dataset.DTO.SetCommentStatusDTO;
import com.example.dataset.VO.CommentWithoutAuditListVO;
import com.example.dataset.mapper.AdminCommentMapper;
import com.example.dataset.service.AdminCommentService;
import com.example.dataset.utils.PageResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminCommentServiceimpl implements AdminCommentService {

    @Autowired
    private AdminCommentMapper adminCommentMapper;

    @Override
    public PageResult getCommentWithoutAudit(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<CommentWithoutAuditListVO> pages = adminCommentMapper.getCommentWithoutAudit();
        return new PageResult(pages.getTotal(), pages.getResult());
    }

    @Override
    public void setCommentStatus(SetCommentStatusDTO setCommentStatusDTO) {
        adminCommentMapper.setCommentStatus(setCommentStatusDTO.getId(), setCommentStatusDTO.getPass() == 0 ? 2 : 1);
    }
}
