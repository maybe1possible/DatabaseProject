package com.example.dataset.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Upload {
    private int materialId;

    private int userId;

    private LocalDateTime uploadTime;
}
