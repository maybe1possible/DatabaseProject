package com.example.dataset.DTO;

import lombok.Data;

@Data
public class UserUpdateDTO {
    private Long userId;
    private String nickname;
    private String school;
    private String major;
    private String password;
    private String sex;
    private Integer status;
    private String mobile;
    private String email;
    private String avatar;
    private String intro;
}
