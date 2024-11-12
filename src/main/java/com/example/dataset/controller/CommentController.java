package com.example.dataset.controller;

import com.example.dataset.DTO.CommentGetDTO;
import com.example.dataset.DTO.CommentPostDTO;
import com.example.dataset.service.CommentService;
import com.example.dataset.utils.PageResult;
import com.example.dataset.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
@Api(tags = "评论")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/postComment")
    @ApiOperation("发表评论")
    public ResultUtils postComment(@RequestBody CommentPostDTO commentPostDTO) {
        commentService.postComment(commentPostDTO);
        return ResultUtils.success("发表成功，正在审核");
    }

    @GetMapping("/getComment")
    @ApiOperation("获取评论")
    public ResultUtils<PageResult> getComment(@RequestBody CommentGetDTO commentGetDTO) {
        return ResultUtils.success(commentService.getCommentPageById(commentGetDTO));
    }
    // TODO 获取我的评论
}
