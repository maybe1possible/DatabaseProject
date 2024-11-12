package com.example.dataset.VO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RankUserVO {
    private int id;
    private String name;
    private int rank;
    private int score;
}
