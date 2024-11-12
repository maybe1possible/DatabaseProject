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
public class StarDirectory {
    private int directoryId;

    private String directoryName;

    private String description;

    private int userId;

    private LocalDateTime time;
}
