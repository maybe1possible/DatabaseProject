package com.example.dataset.service;

import com.example.dataset.DTO.SetUserStatusDTO;
import com.example.dataset.VO.AdminUserVO;
import com.example.dataset.utils.PageResult;

public interface AdminUserService {
    PageResult getAllUser(Integer pageNum, Integer pageSize);

    AdminUserVO getUserById(Integer id);

    void setUserStatus(SetUserStatusDTO setUserStatusDTO);
}
