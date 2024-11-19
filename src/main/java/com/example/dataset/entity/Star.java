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
public class Star {
    private Integer materialId;

    private Integer userId;

    private LocalDateTime starTime;

    private Integer directoryId;
}
