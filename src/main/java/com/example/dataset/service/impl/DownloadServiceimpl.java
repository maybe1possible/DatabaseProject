package com.example.dataset.service.impl;

import com.example.dataset.DTO.MyDownloadPageDTO;
import com.example.dataset.VO.DownloadInfoVO;
import com.example.dataset.mapper.DownloadMapper;
import com.example.dataset.service.DownloadService;
import com.example.dataset.utils.PageResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DownloadServiceimpl implements DownloadService {

    @Autowired
    private DownloadMapper downloadMapper;

    @Override
    public PageResult getMyDownloads(MyDownloadPageDTO myDownloadPageDTO) {
        PageHelper.startPage(myDownloadPageDTO.getPageNum(), myDownloadPageDTO.getPageSize());
        Page<DownloadInfoVO> page = downloadMapper.getDownloadInfoById(myDownloadPageDTO.getUser_id());
        return new PageResult(page.getTotal(), page.getResult());
    }
}
