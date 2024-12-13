package com.example.dataset.controller.admin;

import com.example.dataset.DTO.SetUserStatusDTO;
import com.example.dataset.VO.AdminUserVO;
import com.example.dataset.exception.UserNotExistException;
import com.example.dataset.service.AdminUserService;
import com.example.dataset.utils.PageResult;
import com.example.dataset.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/user")
@Api("管理用户状态")
public class AdminUserController {
    @Autowired
    private AdminUserService adminUserService;

    @GetMapping("/getAllUser")
    @ApiOperation("获取所有用户")
    public ResultUtils<PageResult> getAllUser(Integer pageNum, Integer pageSize){
        PageResult result = adminUserService.getAllUser(pageNum, pageSize);
        return ResultUtils.success(result);
    };

    @GetMapping("/getUserById")
    @ApiOperation("根据ID获取用户")
    public ResultUtils<AdminUserVO> getUserById(Integer id){
        AdminUserVO adminUserVO = new AdminUserVO();
        try {
            adminUserVO = adminUserService.getUserById(id);
        } catch (UserNotExistException e) {
            return ResultUtils.error(e.getMessage());
        }
        return ResultUtils.success(adminUserVO);
    }

    @PostMapping("/setUserStatus")
    @ApiOperation("设置用户状态")
    public ResultUtils setUserStatus(@RequestBody SetUserStatusDTO setUserStatusDTO) {
        adminUserService.setUserStatus(setUserStatusDTO);
        return ResultUtils.success();
    }
}
