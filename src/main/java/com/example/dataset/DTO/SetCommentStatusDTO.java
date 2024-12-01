package com.example.dataset.DTO;

import lombok.Data;

@Data
public class SetCommentStatusDTO {
    private Integer id;
    private Integer pass;//0未通过，1通过
}
