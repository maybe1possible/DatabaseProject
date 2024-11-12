package com.example.dataset.service;

import com.example.dataset.DTO.CommentGetDTO;
import com.example.dataset.DTO.CommentPostDTO;
import com.example.dataset.utils.PageResult;

public interface CommentService {
    void postComment(CommentPostDTO commentPostDTO);

    PageResult getCommentPageById(CommentGetDTO commentGetDTO);
}
