package com.example.dataset.controller.admin;

import com.example.dataset.DTO.SetCommentStatusDTO;
import com.example.dataset.service.AdminCommentService;
import com.example.dataset.utils.PageResult;
import com.example.dataset.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/comment")
@Api("审核评论")
public class AdminCommentController {

    @Autowired
    private AdminCommentService adminCommentService;

    @GetMapping("/getWithoutAudit")
    @ApiOperation("获取未审核评论")
    public ResultUtils<PageResult> getCommentWithoutAudit(Integer pageNum, Integer pageSize) {
        PageResult result = adminCommentService.getCommentWithoutAudit(pageNum, pageSize);
        return ResultUtils.success(result);
    }

    @PostMapping("/setCommentStatus")
    @ApiOperation("设置评论审核结果")
    public ResultUtils setCommentStatus(@RequestBody SetCommentStatusDTO setCommentStatusDTO) {
        adminCommentService.setCommentStatus(setCommentStatusDTO);
        return ResultUtils.success();
    }

}
