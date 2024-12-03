package com.example.dataset.service.impl;

import com.example.dataset.DTO.UploadDownloadDTO;
import com.example.dataset.VO.DownloadInfoVO;
import com.example.dataset.entity.Download;
import com.example.dataset.mapper.DownloadMapper;
import com.example.dataset.service.DownloadService;
import com.example.dataset.utils.PageResult;
import com.example.dataset.utils.ResultUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DownloadServiceimpl implements DownloadService {

    @Autowired
    private DownloadMapper downloadMapper;

    @Override
    public PageResult getMyDownloads(Integer user_id, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<DownloadInfoVO> page = downloadMapper.getDownloadInfoById(user_id);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public void uploadDownloads(UploadDownloadDTO uploadDownloadDTO) {
        Download download = new Download();
        BeanUtils.copyProperties(uploadDownloadDTO, download);
        download.setDownloadTime(LocalDateTime.now());
        try {
            downloadMapper.uploadDownloads(download);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
