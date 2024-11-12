package com.example.dataset.controller;

import com.example.dataset.DTO.UserInfoGetDTO;
import com.example.dataset.DTO.UserLoginDTO;
import com.example.dataset.DTO.UserUpdateDTO;
import com.example.dataset.VO.UserInfoVO;
import com.example.dataset.VO.UserLoginVO;
import com.example.dataset.entity.User;
import com.example.dataset.service.UserService;
import com.example.dataset.utils.JwtUtil;
import com.example.dataset.utils.ResultUtils;
import com.example.dataset.utils.jwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
@Api(tags = "用户接口")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private jwtUtils jwtUtils;

    @PostMapping("/login")
    @ApiOperation("微信登陆")
    public ResultUtils<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDTO) {
        User user = userService.wxLogin(userLoginDTO);

        Map<String, Object> claims = new HashMap<>();

        claims.put("userId", user.getUserId());

        String jwt = JwtUtil.createJWT(jwtUtils.getUserSecretKey(), jwtUtils.getUserTtl(), claims);

        UserLoginVO userLoginVO = UserLoginVO.builder()
                .userId(user.getUserId())
                .openid(user.getOpenid())
                .token(jwt)
                .build();

        return ResultUtils.success(userLoginVO);
    }

    @GetMapping("/getUserInfo")
    @ApiOperation("获取用户信息")
    public ResultUtils<UserInfoVO> getUserInfo(@RequestBody UserInfoGetDTO userInfoGetDTO) {

        User user = userService.getById(userInfoGetDTO.getUser_id());

        if (user == null) {
            return ResultUtils.error("用户不存在");
        }

        UserInfoVO userInfoVO = UserInfoVO.builder()
                .avatar(user.getAvatar())
                .nickname(user.getNickname())
                .email(user.getEmail())
                .intro(user.getIntro())
                .school(user.getSchool())
                .major(user.getMajor())
                .sex(user.getSex())
                .status(user.getStatus())
                .mobile(user.getMobile())
                .build();
        // TODO 完成avatar
        return ResultUtils.success(userInfoVO);
    }

    @PostMapping("/updateInfo")
    @ApiOperation("更新用户信息")
    public ResultUtils updateUserInfo(@RequestBody UserUpdateDTO userUpdateDTO) {
        if (userUpdateDTO.getUserId() == 0) {
            return ResultUtils.error("用户id不能为空");
        }
        userService.updateUserInfo(userUpdateDTO);
        return ResultUtils.success();
    }
}
