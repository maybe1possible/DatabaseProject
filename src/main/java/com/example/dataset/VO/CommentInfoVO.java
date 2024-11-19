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
public class CommentInfoVO {
    Integer commentId;

    String content;

    private Author author;

    LocalDateTime time;

    Integer total;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Author {
        private Integer id;
        private String name;
        private String avatar;
    }
}
