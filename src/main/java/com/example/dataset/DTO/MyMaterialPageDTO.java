package com.example.dataset.DTO;

import lombok.Data;

@Data
public class MyMaterialPageDTO {
    private int userId;

    private Integer page;

    private Integer pageSize;

    private String sort;
}
