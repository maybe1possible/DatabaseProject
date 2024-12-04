package com.example.dataset.service;

import com.example.dataset.DTO.UploadDownloadDTO;
import com.example.dataset.utils.PageResult;
import com.example.dataset.utils.ResultUtils;

public interface DownloadService {
    PageResult getMyDownloads(Integer user_id, Integer pageNum, Integer pageSize);

    void uploadDownloads(UploadDownloadDTO uploadDownloadDTO);

    Integer getIfDownloaded(Integer userId, Integer materialId);

    void deleteDownload(Integer downloadId);
}
