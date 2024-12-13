package com.example.dataset.service.impl;

import com.example.dataset.DTO.SetUserStatusDTO;
import com.example.dataset.VO.AdminUserVO;
import com.example.dataset.exception.UserNotExistException;
import com.example.dataset.mapper.AdminUserMapper;
import com.example.dataset.service.AdminUserService;
import com.example.dataset.utils.PageResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminUserServiceimpl implements AdminUserService {
    @Autowired
    private AdminUserMapper adminUserMapper;

    @Override
    public PageResult getAllUser(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<AdminUserVO> pages = adminUserMapper.getAllUser();
        return new PageResult(pages.getTotal(), pages.getResult());
    }

    @Override
    public AdminUserVO getUserById(Integer id) {
        AdminUserVO adminUserVO = adminUserMapper.getUserById(id);
        if (adminUserVO != null) {
            return adminUserVO;
        }
        throw new UserNotExistException("用户不存在");
    }

    @Override
    public void setUserStatus(SetUserStatusDTO setUserStatusDTO) {
        adminUserMapper.setStatus(setUserStatusDTO.getId(), setUserStatusDTO.getStatus());
    }
}
