package com.example.dataset.controller;

import com.example.dataset.DTO.StarDirectoryCreateDTO;
import com.example.dataset.DTO.StarMaterialDTO;
import com.example.dataset.DTO.StarPageDTO;
import com.example.dataset.exception.DuplicateDirectoryException;
import com.example.dataset.service.StarService;
import com.example.dataset.utils.PageResult;
import com.example.dataset.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/star")
@Api("收藏")
public class StarController {

    @Autowired
    private StarService starService;

    @PostMapping("/makeFavorites")
    @ApiOperation("创建收藏夹")
    public ResultUtils makeFavorites(@RequestBody StarDirectoryCreateDTO starDirectoryCreateDTO) {
        try {
            starService.makeStarDictory(starDirectoryCreateDTO);
            return ResultUtils.success();
        } catch (DuplicateDirectoryException e) {
            return ResultUtils.error(e.getMessage());
        }
    }

    @PostMapping("/postStar")
    @ApiOperation("收藏文章")
    public ResultUtils postStar(@RequestBody StarMaterialDTO starMaterialDTO) {
        try {
            starService.postStar(starMaterialDTO);
            return ResultUtils.success();
        } catch (Exception e) {
            return ResultUtils.error(e.getMessage());
        }
    }

    @GetMapping("/getMyStars")
    @ApiOperation("获取我的收藏")
    public ResultUtils<PageResult> getMyStars(@RequestBody StarPageDTO starPageDTO) {
        return ResultUtils.success(starService.getStars(starPageDTO));
    }


}
