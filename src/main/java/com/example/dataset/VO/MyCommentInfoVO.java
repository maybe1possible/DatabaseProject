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
public class MyCommentInfoVO {
    private Integer material_id;
    private String title;
    private String file_type;
    private String content;
    private LocalDateTime comment_time;
    private Integer state;
}
