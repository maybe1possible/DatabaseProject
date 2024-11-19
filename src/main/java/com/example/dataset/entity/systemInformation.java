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
public class systemInformation {
    private Integer informationId;

    private Integer adminId;

    private String title;

    private String content;

    private LocalDateTime time;
}
