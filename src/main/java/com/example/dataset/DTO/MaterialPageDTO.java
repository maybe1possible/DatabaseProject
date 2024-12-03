package com.example.dataset.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MaterialPageDTO {
    private Integer userId;

    private String type;

    private String keyword;

    private String navName;

    private Integer page;

    private Integer pageSize;

    private String sort;
}
