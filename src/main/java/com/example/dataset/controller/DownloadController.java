package com.example.dataset.controller;

import com.example.dataset.DTO.DeleteDownloadDTO;
import com.example.dataset.DTO.UploadDownloadDTO;
import com.example.dataset.service.DownloadService;
import com.example.dataset.utils.PageResult;
import com.example.dataset.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/download")
@Api("下载")
public class DownloadController {

    @Autowired
    private DownloadService downloadService;

    @GetMapping("/myDownloads")
    @ApiOperation("获得我的下载")
    public ResultUtils<PageResult> myDownloads(@RequestParam Integer user_id, Integer pageNum, Integer pageSize) {
        return ResultUtils.success(downloadService.getMyDownloads(user_id, pageNum, pageSize));
    }

    @PostMapping("/uploadDownloads")
    @ApiOperation("上传下载")
    public ResultUtils uploadDownloads(@RequestBody UploadDownloadDTO uploadDownloadDTO) {
        downloadService.uploadDownloads(uploadDownloadDTO);
        return ResultUtils.success();
    }

    @PostMapping("/deleteDownload")
    @ApiOperation("删除下载")
    public ResultUtils deleteDownload(@RequestBody DeleteDownloadDTO deleteDownloadDTO) {
        downloadService.deleteDownload(deleteDownloadDTO.getDownloadId());
        return ResultUtils.success();
    }
}
