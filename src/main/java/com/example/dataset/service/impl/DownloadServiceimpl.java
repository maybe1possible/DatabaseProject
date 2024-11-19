package com.example.dataset.service.impl;

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
    public PageResult getMyDownloads(int user_id, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<DownloadInfoVO> page = downloadMapper.getDownloadInfoById(user_id);
        return new PageResult(page.getTotal(), page.getResult());
    }
}
