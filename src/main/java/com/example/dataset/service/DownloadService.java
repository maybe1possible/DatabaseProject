package com.example.dataset.service;

import com.example.dataset.utils.PageResult;

public interface DownloadService {
    PageResult getMyDownloads(int user_id, int pageNum, int pageSize);
}
