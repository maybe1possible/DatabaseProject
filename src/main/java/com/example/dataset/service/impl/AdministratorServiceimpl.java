package com.example.dataset.service.impl;

import com.example.dataset.DTO.AdminAddDTO;
import com.example.dataset.DTO.AdminLoginDTO;
import com.example.dataset.entity.Administrator;
import com.example.dataset.mapper.AdministratorMapper;
import com.example.dataset.service.AdministratorService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;

@Service
public class AdministratorServiceimpl implements AdministratorService {

    @Autowired
    private AdministratorMapper administratorMapper;

    @Override
    public Administrator login(AdminLoginDTO adminLoginDTO) {
        String username = adminLoginDTO.getUsername();
        String password = adminLoginDTO.getPassword();

        Administrator administrator = administratorMapper.getByUsername(username);

        if (administrator == null) {
            throw new RuntimeException("账号不存在");
        }

        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!administrator.getPassword().equals(password)) {
            throw new RuntimeException("密码错误");
        }
        return administrator;
    }

    @Override
    @Transactional
    public void save(AdminAddDTO adminAddDTO) {
        if (administratorMapper.getByUsername(adminAddDTO.getUsername()) != null) {
            throw new RuntimeException("账号已存在");
        }
        Administrator administrator = new Administrator();
        BeanUtils.copyProperties(adminAddDTO, administrator);
        administrator.setPassword(DigestUtils.md5DigestAsHex(adminAddDTO.getPassword().getBytes()));
        administrator.setLastOperateTime(LocalDateTime.now());
        administratorMapper.add(administrator);
    }
}
