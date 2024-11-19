package com.example.dataset.controller;

import com.example.dataset.service.RankService;
import com.example.dataset.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/rank")
@Api("排名")
public class RankController {
    @Autowired
    private RankService rankService;

    @GetMapping("/getRankings")
    @ApiOperation("获取排名")
    public ResultUtils<List> getRankings(String type, Integer limit) {
        try {
            List rankByType = rankService.getRankByType(type, limit);
            return ResultUtils.success(rankByType);
        } catch (Exception e) {
            return ResultUtils.error(e.getMessage());
        }
    }

}
