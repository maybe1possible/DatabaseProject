package com.example.dataset.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Administrator {
    private Integer adminId;

    private String username;

    private String name;

    private String password;

    private LocalDateTime lastOperateTime;
}
