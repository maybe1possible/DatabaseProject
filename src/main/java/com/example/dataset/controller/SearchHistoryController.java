package com.example.dataset.controller;

import com.example.dataset.DTO.SearchHistoryAddDTO;
import com.example.dataset.VO.SearchHistoryVO;
import com.example.dataset.service.SearchHistoryService;
import com.example.dataset.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/searchHistory")
@Api(tags = "搜索历史")
public class SearchHistoryController {
    @Autowired
    private SearchHistoryService searchHistoryService;

    @PostMapping("/addSearchHistory")
    @ApiOperation("添加搜索记录")
    public ResultUtils addSearchHistory(@RequestBody SearchHistoryAddDTO searchHistoryAddDTO) {
        searchHistoryService.addSearchHistory(searchHistoryAddDTO.getUserId(), searchHistoryAddDTO.getKeyword());
        return ResultUtils.success();
    }

    @GetMapping("/getSearchHistory")
    @ApiOperation("获取搜索记录")
    public ResultUtils<List<SearchHistoryVO>> getSearchHistory(@RequestParam Integer userId) {
        return ResultUtils.success(searchHistoryService.getSearchHistory(userId));
    }

    //TODO 定时请求历史记录
}
