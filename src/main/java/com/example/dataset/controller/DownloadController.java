package com.example.dataset.controller;

import com.example.dataset.DTO.MyDownloadPageDTO;
import com.example.dataset.VO.DownloadInfoVO;
import com.example.dataset.service.DownloadService;
import com.example.dataset.utils.PageResult;
import com.example.dataset.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/download")
@Api("下载")
public class DownloadController {

    @Autowired
    private DownloadService downloadService;

    @GetMapping("/myDownloads")
    @ApiOperation("获得我的下载")
    public ResultUtils<PageResult> myDownloads(@RequestBody MyDownloadPageDTO myDownloadPageDTO) {
        return ResultUtils.success(downloadService.getMyDownloads(myDownloadPageDTO));
    }
    // TODO 上传下载
}
