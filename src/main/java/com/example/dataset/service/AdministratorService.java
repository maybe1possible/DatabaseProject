package com.example.dataset.service;

import com.example.dataset.DTO.AdminAddDTO;
import com.example.dataset.DTO.AdminLoginDTO;
import com.example.dataset.entity.Administrator;

public interface AdministratorService {

    Administrator login(AdminLoginDTO adminLoginDTO);

    void save(AdminAddDTO adminAddDTO);
}
