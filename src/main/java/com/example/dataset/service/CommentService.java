package com.example.dataset.service;

import com.example.dataset.DTO.CommentPostDTO;
import com.example.dataset.utils.PageResult;

public interface CommentService {
    void postComment(CommentPostDTO commentPostDTO);

    PageResult getCommentPageById(int article_id, int pageSize, int pageNumber);

    PageResult getMyCommentById(int user_id, int pageSize, int pageNumber);
}
