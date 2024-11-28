package com.example.dataset.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private Integer userId;

    private String nickname;

    private String school;

    private String major;

    private String password;

    private String sex;

    private Integer status;//0正常，1封禁，2禁言

    private String mobile;

    private String email;

    private Integer score;

    private LocalDateTime genTime;

    private LocalDateTime lastLoginTime;

    private String openid;

    private String avatar;

    private String intro;
}
