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
public class MaterialListVO {
    private Integer id;

    private String title;

    private String description;

    private String author;

    private LocalDateTime uploadTime;

    private String heat;
}
