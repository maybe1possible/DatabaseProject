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
    private int adminId;

    private String account;

    private String password;

    private LocalDateTime lastOperateTime;
}
