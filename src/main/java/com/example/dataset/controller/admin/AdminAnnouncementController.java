package com.example.dataset.controller.admin;

import com.example.dataset.DTO.AnnouncementPublishDTO;
import com.example.dataset.service.AdminAnnouncementService;
import com.example.dataset.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/announcement")
@Api("公告")
public class AdminAnnouncementController {
    @Autowired
    private AdminAnnouncementService adminAnnouncementService;

    @PostMapping("/publish")
    @ApiOperation("新增公告")
    public ResultUtils publish(@RequestBody AnnouncementPublishDTO announcementPublishDTO) {
        adminAnnouncementService.publish(announcementPublishDTO);
        return ResultUtils.success();
    }
}
