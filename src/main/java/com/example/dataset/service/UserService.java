package com.example.dataset.service;

import com.example.dataset.DTO.UserLoginDTO;
import com.example.dataset.DTO.UserUpdateDTO;
import com.example.dataset.entity.User;

public interface UserService {
    User wxLogin(UserLoginDTO userLoginDTO);

    User getById(int userId);

    void updateUserInfo(UserUpdateDTO userUpdateDTO);
}
