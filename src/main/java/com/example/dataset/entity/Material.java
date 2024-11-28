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
    private Integer materialId;
    // 0: 仅查看 1: 可下载 2:未通过 3:仅查看待审核 4:可下载待审核
    private Integer state;

    private Integer userId;
    // 服务器地址
    private String contentPath;

    private String tag;

    private String fileType;

    private String description;

    private String title;

    private LocalDateTime publishTime;

    private Integer official;//0为非官方

    private String size;
}
