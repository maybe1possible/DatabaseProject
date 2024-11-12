package com.example.dataset.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Material {
    private int materialId;
    // 0: 仅查看 1: 可下载 2:待审核 3:未通过
    private int state;

    private int userId;
    // 服务器地址
    private String contentPath;

    private String tag;

    private String fileType;

    private String description;

    private String title;

    private LocalDateTime publishTime;

    private Integer official;

    private String size;
}
