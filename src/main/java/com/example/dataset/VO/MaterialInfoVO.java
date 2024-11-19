package com.example.dataset.VO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MaterialInfoVO {
    private Integer material_id;

    private String title;

    private String description;

    private List<String> tags;

    private LocalDateTime publish_time;

    private Author author;

    private String file_url;

    private String limit;

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
