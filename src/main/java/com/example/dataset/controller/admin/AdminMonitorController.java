package com.example.dataset.controller.admin;

import com.example.dataset.VO.ArticleMonitorVO;
import com.example.dataset.VO.DownloadMonitorVO;
import com.example.dataset.VO.UserMonitorVO;
import com.example.dataset.service.AdminMonitorService;
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
@RequestMapping("/admin/monitor")
@Api("软件监控")
public class AdminMonitorController {
    @Autowired
    private AdminMonitorService adminMonitorService;

    @GetMapping("/getDownloadMonitor")
    @ApiOperation("下载量监控")
    public ResultUtils<List<DownloadMonitorVO>> getDownloadMonitor(@RequestParam String type) { // month week day
        List<DownloadMonitorVO> results = adminMonitorService.getDownloadMonitor(type);
        return ResultUtils.success(results);
    }

    @GetMapping("/getArticleMonitor")
    @ApiOperation("上传资料监控")
    public ResultUtils<List<ArticleMonitorVO>> getArticleMonitor(@RequestParam String type) {
        List<ArticleMonitorVO> results = adminMonitorService.getArticleMonitor(type);
        return ResultUtils.success(results);
    }

    @GetMapping("/getUserMonitor")
    @ApiOperation("用户监控")
    public ResultUtils<List<UserMonitorVO>> getUserMonitor(@RequestParam String type) {
        List<UserMonitorVO> results = adminMonitorService.getUserMonitor(type);
        return ResultUtils.success(results);
    }

}
