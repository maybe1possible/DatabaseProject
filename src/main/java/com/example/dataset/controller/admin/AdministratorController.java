package com.example.dataset.controller.admin;

import com.example.dataset.DTO.AdminAddDTO;
import com.example.dataset.DTO.AdminLoginDTO;
import com.example.dataset.VO.AdminLoginVO;
import com.example.dataset.entity.Administrator;
import com.example.dataset.service.AdministratorService;
import com.example.dataset.utils.JwtUtil;
import com.example.dataset.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/administrator")
@Api(tags = "管理员用户相关操作")
public class AdministratorController {

    @Autowired
    private AdministratorService administratorService;
    @Autowired
    private com.example.dataset.utils.jwtUtils jwtUtils;

    @PostMapping("/login")
    @ApiOperation("登录")
    public ResultUtils<AdminLoginVO> login(@RequestBody AdminLoginDTO adminLoginDTO) {
        Administrator administrator = administratorService.login(adminLoginDTO);

        Map<String, Object> claims = new HashMap<>();
        claims.put("AdminID", administrator.getAdminId());
        String token = JwtUtil.createJWT(
                jwtUtils.getAdminSecretKey(),
                jwtUtils.getAdminTtl(),
                claims);

        AdminLoginVO adminLoginVO = AdminLoginVO.builder()
                .id(administrator.getAdminId())
                .name(administrator.getName())
                .username(administrator.getUsername())
                .token(token)
                .build();

        return ResultUtils.success(adminLoginVO);
    }

    @PostMapping("/add")
    @ApiOperation("新增管理员")
    public ResultUtils save(@RequestBody AdminAddDTO adminAddDTO) {
         administratorService.save(adminAddDTO);
         return ResultUtils.success();
    }
}
