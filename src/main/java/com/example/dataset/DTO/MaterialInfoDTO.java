package com.example.dataset.DTO;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class MaterialInfoDTO {
    private Integer materialId;

    private String title;

    private String description;

    private LocalDateTime publishTime;

    private Integer authorId;

    private String authorName;

    private String avatar;

    private Integer status;

    private String content_path;

    private List<String> tags;
}
