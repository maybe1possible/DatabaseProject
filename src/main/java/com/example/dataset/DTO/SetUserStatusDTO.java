package com.example.dataset.DTO;

import lombok.Data;

@Data
public class SetUserStatusDTO {
    private Integer id;
    private Integer status;//0正常 1禁言 2封禁
}
