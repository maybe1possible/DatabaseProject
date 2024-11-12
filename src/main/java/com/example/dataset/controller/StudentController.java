package com.example.dataset.controller;

import com.example.dataset.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student")
@Api(tags = "查询学生")
public class StudentController {
    @Autowired
    StudentService studentService;

    @GetMapping("/get")
    @ApiOperation("查询大于100分的学生")
    public List<String> GetName() {
        return studentService.list();
    }

}
