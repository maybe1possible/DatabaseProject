package com.example.dataset.service;

import com.example.dataset.utils.PageResult;

public interface DownloadService {
    PageResult getMyDownloads(Integer user_id, Integer pageNum, Integer pageSize);
}
