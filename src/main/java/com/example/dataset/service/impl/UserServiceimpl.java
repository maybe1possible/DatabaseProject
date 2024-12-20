package com.example.dataset.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.example.dataset.DTO.UserLoginDTO;
import com.example.dataset.DTO.UserUpdateDTO;
import com.example.dataset.config.AliOssProperties;
import com.example.dataset.config.WechatConfiguration;
import com.example.dataset.entity.User;
import com.example.dataset.exception.LoginFailedException;
import com.example.dataset.mapper.UserMapper;
import com.example.dataset.service.UserService;
import com.example.dataset.utils.HttpClientUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.beans.Transient;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceimpl implements UserService {
    public static final String WX_LOGIN = "https://api.weixin.qq.com/sns/jscode2session";

    @Autowired
    private WechatConfiguration wechatConfiguration;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AliOssProperties aliOssProperties;

    @Override
    @Transient
    public User wxLogin(UserLoginDTO userLoginDTO) {
        String openid = getOpenid(userLoginDTO.getCode());

        if (openid == null) {
            throw new LoginFailedException("登陆失败");
        }

        User user = userMapper.getByOpenid(openid);

        if (user == null) {
            user = User.builder()
                    .openid(openid)
                    .genTime(LocalDateTime.now())
                    .status(0)
                    .build();
            userMapper.insert(user);
        }
        user.setLastLoginTime(LocalDateTime.now());
        userMapper.setLastLoginTime(user.getUserId(), LocalDateTime.now());

        return user;
    }

    private String getOpenid(String code) {
        Map<String, String> map = new HashMap<>();
        map.put("appid", wechatConfiguration.getAppid());
        map.put("secret", wechatConfiguration.getSecret());
        map.put("js_code", code);
        map.put("grant_type", "authorization_code");
        String json = HttpClientUtils.doGet(WX_LOGIN, map);

        JSONObject jsonObject = JSON.parseObject(json);
        return jsonObject.getString("openid");
    }

    public String download(Integer userId) {
        OSS ossClient = new OSSClientBuilder().build(aliOssProperties.getEndpoint(), aliOssProperties.getAccessKeyId(), aliOssProperties.getAccessKeySecret());

        String filename = userMapper.getAvatarName(userId);

        if (filename == null) {
            return null;
        }

        Date expiration = new Date(System.currentTimeMillis() + 3600 * 1000);

        return ossClient.generatePresignedUrl(aliOssProperties.getBucketName(), filename, expiration).toString();
    }

    @Override
    public User getById(Integer userId) {
        User user = userMapper.getById(userId);
        user.setAvatar(download(user.getUserId()));
        return user;
    }

    @Override
    public void updateUserInfo(User user) {
        userMapper.update(user);
    }
}
