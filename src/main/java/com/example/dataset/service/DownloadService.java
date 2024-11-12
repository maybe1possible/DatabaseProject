package com.example.dataset.service;

import com.example.dataset.DTO.MyDownloadPageDTO;
import com.example.dataset.utils.PageResult;

public interface DownloadService {
    PageResult getMyDownloads(MyDownloadPageDTO myDownloadPageDTO);
}
