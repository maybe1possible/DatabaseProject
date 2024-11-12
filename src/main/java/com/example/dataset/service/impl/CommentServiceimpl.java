package com.example.dataset.service.impl;

import com.example.dataset.DTO.CommentGetDTO;
import com.example.dataset.DTO.CommentPostDTO;
import com.example.dataset.DTO.MyCommentGetDTO;
import com.example.dataset.VO.CommentInfoVO;
import com.example.dataset.mapper.CommentMapper;
import com.example.dataset.service.CommentService;
import com.example.dataset.utils.PageResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentServiceimpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;

    @Override
    public void postComment(CommentPostDTO commentPostDTO) {
        commentMapper.postComment(commentPostDTO.getArticle_id(), commentPostDTO.getComment_content(), commentPostDTO.getUserId(), LocalDateTime.now());
    }

    @Override
    public PageResult getCommentPageById(CommentGetDTO commentGetDTO) {
        PageHelper.startPage(commentGetDTO.getPageNumber(), commentGetDTO.getPageSize());
        Page<CommentInfoVO> page = commentMapper.getCommentPageById(commentGetDTO.getArticle_id());
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public PageResult getMyCommentById(MyCommentGetDTO mycommentGetDTO) {
        PageHelper.startPage(mycommentGetDTO.getPageNum(), mycommentGetDTO.getPageSize());
        Page<MyCommentGetDTO> page = commentMapper.getMyCommentById(mycommentGetDTO.getId());
        return new PageResult(page.getTotal(), page.getResult());
    }
}
