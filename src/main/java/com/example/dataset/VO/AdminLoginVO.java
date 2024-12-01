package com.example.dataset.VO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminLoginVO {
    private Integer id;
    private String username;
    private String name;
    private String token;
}
