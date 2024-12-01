package com.example.dataset.DTO;

import lombok.Data;

@Data
public class SetArticleStatusDTO {
    private Integer id;
    private Integer pass;//0为不通过，1为通过
}
