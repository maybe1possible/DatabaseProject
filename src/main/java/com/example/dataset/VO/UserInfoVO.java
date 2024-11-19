package com.example.dataset.VO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoVO {
    private String nickname;
    private String school;
    private String major;
//    private String password;
    private String sex;
    private Integer status;
    private String mobile;
    private String email;
    private String avatar;
    private String intro;
}
