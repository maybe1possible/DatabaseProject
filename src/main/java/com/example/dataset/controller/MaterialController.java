package com.example.dataset.controller;


import com.example.dataset.DTO.ChangeCompetenceDTO;
import com.example.dataset.DTO.DeleteArticleDTO;
import com.example.dataset.DTO.MaterialInfoDTO;
import com.example.dataset.DTO.MaterialPageDTO;
import com.example.dataset.VO.MaterialInfoVO;
import com.example.dataset.entity.Material;
import com.example.dataset.service.DownloadService;
import com.example.dataset.service.MaterialService;
import com.example.dataset.service.StarService;
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
@RequestMapping("/api/article")
@Api(tags = "资料接口")
public class MaterialController {
    @Autowired
    MaterialService materialService;

    @Autowired
    DownloadService downloadService;

    @Autowired
    StarService starService;

    @Autowired
    private AliOssUtil aliOssUtil;

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
                .official(0)
                .size(fileSizeWithUnit)
                .build();

        String[] Tags = tags.split(",");

        materialService.upload(material, Tags);

        return ResultUtils.success();
    }

    @PostMapping("/download")
    @ApiOperation("下载资料")
    public ResultUtils<String> download(@RequestParam("material_id") Integer material_id) {
        String downloadUrl = materialService.download(material_id);
        if (downloadUrl == null) {
            return ResultUtils.error("文件不存在");
        }
        return ResultUtils.success(downloadUrl);
    }

    @GetMapping("/getArticles")
    @ApiOperation("获取资料列表")
    public ResultUtils<PageResult> getArticles(Integer userId, String type, String keyword, String navName, Integer pageNumber, Integer pageSize, String sort) {
        MaterialPageDTO materialPageDTO = MaterialPageDTO.builder()
                .userId(userId)
                .type(type)
                .keyword(keyword)
                .navName(navName)
                .page(pageNumber)
                .pageSize(pageSize)
                .sort(sort)
                .build();
        if (materialPageDTO.getType().equals("search")) {
            if (materialPageDTO.getKeyword().isEmpty()) return ResultUtils.error("请输入关键词");
            PageResult result = materialService.pageSearchByKeyword(materialPageDTO);
            return ResultUtils.success(result);
        }
        if (materialPageDTO.getType().equals("nav")) {
            if (!Objects.equals(materialPageDTO.getNavName(), "official") && !Objects.equals(materialPageDTO.getNavName(), "all") && !Objects.equals(materialPageDTO.getNavName(), "recommend")) return ResultUtils.error("navName参数错误");
            PageResult result = materialService.pageSearchByNav(materialPageDTO);
            return ResultUtils.success(result);
        }
        return ResultUtils.error("参数错误");
    }

    @GetMapping("/getMyPostArticle")
    @ApiOperation("获取用户上传的资料列表")
    public ResultUtils<PageResult> getMyPostArticles(Integer userId, Integer page, Integer pageSize, String sort) {
        PageResult result = materialService.pageSearchById(userId, page, pageSize, sort);
        return ResultUtils.success(result);
    }

    @GetMapping("/getArticleDetail")
    @ApiOperation("获取资料细节")
    public ResultUtils<MaterialInfoVO> getArticleDetail(@RequestParam("user_id") Integer user_id, @RequestParam("material_id") Integer material_id) {
        MaterialInfoDTO materialInfoDTO = materialService.getMaterialById(material_id);
        if (materialInfoDTO.getStatus() == 5) {
            return ResultUtils.error("文章已被作者删除");
        }
        Integer downloaded = downloadService.getIfDownloaded(user_id, material_id);
        Integer stared = starService.getIfStared(user_id, material_id);
        MaterialInfoVO materialInfoVO = MaterialInfoVO.builder()
                .material_id(material_id)
                .title(materialInfoDTO.getTitle())
                .description(materialInfoDTO.getDescription())
                .tags(materialInfoDTO.getTags())
                .downloaded(downloaded)
                .stared(stared)
                .publish_time(materialInfoDTO.getPublishTime())
                .author(new MaterialInfoVO.Author(materialInfoDTO.getAuthorId(), materialInfoDTO.getAuthorName(), materialInfoDTO.getAvatar()))
                .file_url(materialInfoDTO.getContent_path())
                .limit(materialInfoDTO.getStatus() == 0 || materialInfoDTO.getStatus() == 3 ? "onlyView" : "downLoad")
                .build();
        return ResultUtils.success(materialInfoVO);
    }

    @PostMapping("/deleteArticle")
    @ApiOperation("删除上传的文件")
    public ResultUtils deleteArticle(@RequestBody DeleteArticleDTO deleteArticleDTO) {
        try {
            materialService.deleteArticle(deleteArticleDTO);
        } catch (Exception e) {
            return ResultUtils.error(e.getMessage());
        }
        return ResultUtils.success();
    }

    @PostMapping("/changeCompetence")
    @ApiOperation("修改文件权限")
    public ResultUtils changeCompetence(@RequestBody ChangeCompetenceDTO changeCompetenceDTO) {
        materialService.changeCompetence(changeCompetenceDTO);
        return ResultUtils.success();
    }
}
