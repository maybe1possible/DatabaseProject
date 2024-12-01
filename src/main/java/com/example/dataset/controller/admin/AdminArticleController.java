package com.example.dataset.controller.admin;

import com.example.dataset.DTO.SetArticleStatusDTO;
import com.example.dataset.entity.Material;
import com.example.dataset.service.AdminArticleService;
import com.example.dataset.service.MaterialService;
import com.example.dataset.utils.AliOssUtil;
import com.example.dataset.utils.PageResult;
import com.example.dataset.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/admin/article")
@Api(tags = "审核文章")
public class AdminArticleController {

    @Autowired
    private AdminArticleService adminArticleService;

    @Autowired
    private MaterialService materialService;

    @Autowired
    private AliOssUtil aliOssUtil;

    @GetMapping("/getWithoutAudit")
    @ApiOperation("获取待审核文章")
    public ResultUtils<PageResult> getArticleWithoutAudit(Integer pageNum, Integer pageSize) {
        PageResult result = adminArticleService.getArticleWithoutAudit(pageNum, pageSize);
        return ResultUtils.success(result);
    }

    @PostMapping("/setArticleStatus")
    public ResultUtils setArticleStatus(@RequestBody SetArticleStatusDTO setArticleStatusDTO) {
        adminArticleService.setArticleStatus(setArticleStatusDTO);
        return ResultUtils.success();
    }

    private String convertFileSizeToString(long sizeInBytes) {
        if (sizeInBytes < 1024) {
            // 小于1KB，以字节为单位
            return sizeInBytes + " B";
        } else if (sizeInBytes < 1024 * 1024) {
            // 小于1MB，以KB为单位
            return (sizeInBytes / 1024) + " KB";
        } else {
            // 大于等于1MB，以MB为单位
            return (sizeInBytes / (1024 * 1024)) + " MB";
        }
    }

    @PostMapping("/upload")
    @ApiOperation("上传资料")
    public ResultUtils upload(@RequestParam("file") MultipartFile file,
                              @RequestParam("user_id") Integer user_id,
                              @RequestParam("title") String title,
                              @RequestParam("description") String description,
                              @RequestParam("tags") String tags,
                              @RequestParam("limit") String limit) throws IOException {

        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String objectName = UUID.randomUUID().toString() + extension;

        long fileSizeInBytes = file.getSize(); // 获取文件大小（字节）
        String fileSizeWithUnit = convertFileSizeToString(fileSizeInBytes);

        String filePath = aliOssUtil.upload(file.getBytes(), objectName);

        Material material = Material.builder()
                .userId(user_id)
                .state(((Objects.equals(limit, "onlyView")) ? 0 : 1) + 3)
                .fileType(extension)
                .title(title)
                .description(description)
                .publishTime(LocalDateTime.now())
                .contentPath(objectName)
                .official(1)
                .size(fileSizeWithUnit)
                .build();

        String[] Tags = tags.split(",");

        materialService.upload(material, Tags);

        return ResultUtils.success();
    }

}
