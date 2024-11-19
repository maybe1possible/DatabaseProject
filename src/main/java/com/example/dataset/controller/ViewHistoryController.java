package com.example.dataset.controller;

import com.example.dataset.DTO.ViewHistoryAddDTO;
import com.example.dataset.service.ViewHistoryService;
import com.example.dataset.utils.PageResult;
import com.example.dataset.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/history")
@Api(tags = "浏览记录")
public class ViewHistoryController {
    @Autowired
    private ViewHistoryService viewHistoryService;

    @PostMapping("/addViewHistory")
    @ApiOperation("添加浏览记录")
    public ResultUtils addViewHistory(@RequestBody ViewHistoryAddDTO viewHistoryAddDTO) {
        viewHistoryService.addViewHistory(viewHistoryAddDTO.getUser_id(), viewHistoryAddDTO.getArticle_id());
        return ResultUtils.success();
    }

    @GetMapping("/getViewHistory")
    @ApiOperation("查找浏览记录")
    public ResultUtils<PageResult> getViewHistory(Integer user_id, Integer pageSize, Integer pageNum) {
        return ResultUtils.success(viewHistoryService.getViewHistory(user_id, pageSize, pageNum));
    }

}
