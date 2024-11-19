package com.example.dataset.controller;

import com.example.dataset.service.DownloadService;
import com.example.dataset.utils.PageResult;
import com.example.dataset.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ResultUtils<PageResult> myDownloads(@RequestParam int user_id, int pageNum, int pageSize) {
        return ResultUtils.success(downloadService.getMyDownloads(user_id, pageNum, pageSize));
    }
    // TODO 上传下载
}
