package com.example.dataset.DTO;

import lombok.Data;

@Data
public class CommentPostDTO {
    Integer userId;

    Integer article_id;

    String comment_content;
}
