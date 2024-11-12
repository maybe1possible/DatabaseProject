package com.example.dataset.DTO;

import lombok.Data;

@Data
public class CommentGetDTO {
    int article_id;

    int pageSize;

    int pageNumber;
}
