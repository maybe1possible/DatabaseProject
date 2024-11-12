package com.example.dataset.controller;

import com.example.dataset.VO.AnnouncementInfoVO;
import com.example.dataset.service.SystemInfoService;
import com.example.dataset.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/announcement")
@Api("公告接口")
public class SystemInfoController {

    @Autowired
    private SystemInfoService systemInfoService;

    @GetMapping("/getAnnouncement")
    @ApiOperation("获取公告")
    public ResultUtils<List<AnnouncementInfoVO>> getAnnouncement(Integer limit) {
        return ResultUtils.success(systemInfoService.getAnnouncement(limit));
    }

    @GetMapping("/getAnnouncementDetail")
    @ApiOperation("获得公告详情")
    public ResultUtils<String> getAnnouncementDetail(Integer announcementId) {
        return ResultUtils.success(systemInfoService.getAnnouncementDetail(announcementId));
    }
}
