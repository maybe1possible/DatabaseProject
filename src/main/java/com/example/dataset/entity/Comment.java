package com.example.dataset.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private Integer comment_id;

    private Integer materialId;

    private Integer UserId;

    private String content;

    private LocalDateTime commentTime;

    // 0:待审核 1:审核通过 2:审核不通过
    private Integer state;
}
