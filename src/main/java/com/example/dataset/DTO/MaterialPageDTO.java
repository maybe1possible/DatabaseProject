package com.example.dataset.DTO;

import lombok.Data;

@Data
public class MaterialPageDTO {
    private String type;

    private String keyword;

    private String navName;

    private Integer page;

    private Integer pageSize;

    private String sort;
}
