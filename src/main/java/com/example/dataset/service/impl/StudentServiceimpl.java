package com.example.dataset.service.impl;

import com.example.dataset.mapper.StudentMapper;
import com.example.dataset.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class StudentServiceimpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<String> list() {
        return studentMapper.list();
    }
}
