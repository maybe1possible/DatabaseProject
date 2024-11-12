package com.example.dataset.VO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MaterialListVO {
    private Integer id;

    private String title;

    private String description;

    private String author;

    private String uploadTime;

    private String heat;
}
