package com.example.dataset.controller;

import com.example.dataset.DTO.UserLoginDTO;
import com.example.dataset.DTO.UserUpdateDTO;
import com.example.dataset.VO.UserInfoVO;
import com.example.dataset.VO.UserLoginVO;
import com.example.dataset.entity.User;
import com.example.dataset.service.UserService;
import com.example.dataset.utils.AliOssUtil;
import com.example.dataset.utils.JwtUtil;
import com.example.dataset.utils.ResultUtils;
import com.example.dataset.utils.jwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
@Api(tags = "用户接口")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private jwtUtils jwtUtils;

    @Autowired
    private AliOssUtil aliOssUtil;

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
//    public ResultUtils<UserInfoVO> getUserInfo(UserInfoGetDTO userInfoGetDTO) {
    public ResultUtils<UserInfoVO> getUserInfo(Integer user_id) {
        User user = userService.getById(user_id);

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
        return ResultUtils.success(userInfoVO);
    }

    @PostMapping("/updateInfo")
    @ApiOperation("更新用户信息")
    public ResultUtils updateUserInfo(@RequestParam("avatar") MultipartFile avatar,
                                      @RequestParam("userId") Integer userId,
                                      String nickname,
                                      String school,
                                      String sex,
                                      String intro) throws IOException {
        String originalFilename = avatar.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String objectName = UUID.randomUUID().toString() + extension;

        String filePath = aliOssUtil.upload(avatar.getBytes(), objectName);

        User user = User.builder()
                        .userId(userId)
                .nickname(nickname)
                .school(school)
                .sex(sex)
                .intro(intro)
                .avatar(objectName)
                .build();

        userService.updateUserInfo(user);
        return ResultUtils.success();
    }
}
