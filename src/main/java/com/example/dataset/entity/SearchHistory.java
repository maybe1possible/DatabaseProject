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
public class SearchHistory {
    private Integer searchHistoryId;
    private LocalDateTime time;
    private Integer userId;
    private String keyContent;
}
