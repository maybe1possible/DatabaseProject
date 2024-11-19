package com.example.dataset.service;

import com.example.dataset.DTO.CommentPostDTO;
import com.example.dataset.utils.PageResult;

public interface CommentService {
    void postComment(CommentPostDTO commentPostDTO);

    PageResult getCommentPageById(Integer article_id, Integer pageSize, Integer pageNumber);

    PageResult getMyCommentById(Integer user_id, Integer pageSize, Integer pageNumber);
}
