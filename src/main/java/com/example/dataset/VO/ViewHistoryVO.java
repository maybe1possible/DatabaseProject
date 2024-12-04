package com.example.dataset.VO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ViewHistoryVO {
    private Integer viewHistoryId;

    private Integer materialId;

    private String title;

    private LocalDateTime time;

    private String nickname;

    private Integer score;
}
