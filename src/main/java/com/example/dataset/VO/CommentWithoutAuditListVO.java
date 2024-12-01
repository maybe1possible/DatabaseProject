package com.example.dataset.VO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentWithoutAuditListVO {
    private Integer id;
    private String title;
    private String content;
    private LocalDateTime commentTime;
    private Integer userId;
}
