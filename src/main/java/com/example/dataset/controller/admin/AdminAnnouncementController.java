package com.example.dataset.controller.admin;

import com.example.dataset.DTO.AnnouncementDeleteDTO;
import com.example.dataset.DTO.AnnouncementPublishDTO;
import com.example.dataset.DTO.AnnouncementSetDTO;
import com.example.dataset.VO.AdminAnnouncementInfoVO;
import com.example.dataset.service.AdminAnnouncementService;
import com.example.dataset.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/announcement")
@Api("公告")
public class AdminAnnouncementController {
    @Autowired
    private AdminAnnouncementService adminAnnouncementService;

    @GetMapping("/get")
    @ApiOperation("获取公告")
    public ResultUtils<List<AdminAnnouncementInfoVO>> get() {
        return ResultUtils.success(adminAnnouncementService.getAllAnnouncement());
    }

    @PostMapping("/publish")
    @ApiOperation("新增公告")
    public ResultUtils publish(@RequestBody AnnouncementPublishDTO announcementPublishDTO) {
        adminAnnouncementService.publish(announcementPublishDTO);
        return ResultUtils.success();
    }

    @PostMapping("/delete")
    @ApiOperation("删除公告")
    public ResultUtils delete(@RequestBody AnnouncementDeleteDTO announcementDeleteDTO) {
        adminAnnouncementService.delete(announcementDeleteDTO.getId());
        return ResultUtils.success();
    }

    @PostMapping("/set")
    @ApiOperation("修改公告")
    public ResultUtils set(@RequestBody AnnouncementSetDTO announcementSetDTO) {
        adminAnnouncementService.set(announcementSetDTO);
        return ResultUtils.success();
    }
}
