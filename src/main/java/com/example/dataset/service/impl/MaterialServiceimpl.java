package com.example.dataset.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.example.dataset.DTO.ChangeCompetenceDTO;
import com.example.dataset.DTO.DeleteArticleDTO;
import com.example.dataset.DTO.MaterialInfoDTO;
import com.example.dataset.DTO.MaterialPageDTO;
import com.example.dataset.VO.MaterialListVO;
import com.example.dataset.VO.MyMaterialListVO;
import com.example.dataset.config.AliOssProperties;
import com.example.dataset.entity.Material;
import com.example.dataset.mapper.MaterialMapper;
import com.example.dataset.mapper.MaterialTagMapper;
import com.example.dataset.mapper.SearchHistoryMapper;
import com.example.dataset.mapper.TagMapper;
import com.example.dataset.service.MaterialService;
import com.example.dataset.service.SearchHistoryService;
import com.example.dataset.utils.PageResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Service
@Slf4j
public class MaterialServiceimpl implements MaterialService {

    @Autowired
    private MaterialMapper materialMapper;

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private MaterialTagMapper materialTagMapper;

    @Autowired
    private SearchHistoryService searchHistoryService;

    @Autowired
    private AliOssProperties aliOssProperties;



    @Override
    @Transactional
    public void upload(Material material, String[] tags) {
        materialMapper.upload(material);

        Integer[] tagIds = new Integer[tags.length];

        for (int i = 0; i < tags.length; i++) {
            tagIds[i] = tagMapper.getTagId(tags[i]);
            if (tagIds[i] == null || tagIds[i] == 0) {
                tagMapper.insert(tags[i]);
                tagIds[i] = tagMapper.getTagId(tags[i]);
            }
        }

        materialTagMapper.insertTags(material.getMaterialId(), tagIds);

    }

    @Override
    public String download(Integer materialId) {
        OSS ossClient = new OSSClientBuilder().build(aliOssProperties.getEndpoint(), aliOssProperties.getAccessKeyId(), aliOssProperties.getAccessKeySecret());

        String filename = materialMapper.getFilename(materialId);

        if (filename == null) {
            return null;
        }

        Date expiration = new Date(System.currentTimeMillis() + 3600 * 1000);

        return ossClient.generatePresignedUrl(aliOssProperties.getBucketName(), filename, expiration).toString().replace("http://","https://");
    }

    @Override
    @Transactional
    public PageResult pageSearchByKeyword(MaterialPageDTO materialPageDTO) {
        PageHelper.startPage(materialPageDTO.getPage(), materialPageDTO.getPageSize());
        Page<MaterialListVO> page = materialMapper.pageSearchByKeyword(materialPageDTO);
        log.info(String.valueOf(page.getTotal()));
        searchHistoryService.addSearchHistory(materialPageDTO.getUserId(), materialPageDTO.getKeyword());
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public PageResult pageSearchByNav(MaterialPageDTO materialPageDTO) {
        PageHelper.startPage(materialPageDTO.getPage(), materialPageDTO.getPageSize());
        Page<MaterialListVO> page = null;
        if (materialPageDTO.getNavName().equals("recommend")) {
            page = materialMapper.pageSearchByRecommend(materialPageDTO);
        } else if (materialPageDTO.getNavName().equals("official")) {
            page = materialMapper.pageSearchByOfficial(materialPageDTO);
        } else {
            page = materialMapper.pageSearchAll(materialPageDTO);
        }
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public PageResult pageSearchById(Integer userId, Integer page, Integer pageSize, String sort) {
        PageHelper.startPage(page, pageSize);
        Page<MyMaterialListVO> pages = materialMapper.pageSearchById(userId, sort);
        for (MyMaterialListVO myMaterialListVO : pages.getResult()) {
            // 0: 仅查看 1: 可下载 2:未通过 3:仅查看待审核 4:可下载待审核
            if (myMaterialListVO.getStatus() == 0 || myMaterialListVO.getStatus() == 3) {
                myMaterialListVO.setCompetence("仅查看");
            } else if (myMaterialListVO.getStatus() == 1 || myMaterialListVO.getStatus() == 4) {
                myMaterialListVO.setCompetence("可下载");
            }
            if (myMaterialListVO.getStatus() < 2) {
                myMaterialListVO.setPending("审核通过");
            } else if (myMaterialListVO.getStatus() == 2) {
                myMaterialListVO.setPending("审核未通过");
            } else {
                myMaterialListVO.setPending("待审核");
            }
        }
        return new PageResult(pages.getTotal(), pages.getResult());
    }

    @Override
    @Transactional
    public MaterialInfoDTO getMaterialById(Integer materialId) {
        MaterialInfoDTO materialInfoDTO = materialMapper.getMaterialInfoById(materialId);
        materialInfoDTO.setContent_path(download(materialId));
        materialInfoDTO.setTags(tagMapper.getTagsByMaterialId(materialId));
        return materialInfoDTO;
    }

    @Override
    @Transactional
    public void deleteArticle(DeleteArticleDTO deleteArticleDTO) {
        Integer userId = materialMapper.getUserId(deleteArticleDTO.getArticleId());
        if (!Objects.equals(userId, deleteArticleDTO.getUserId())) {
            throw new RuntimeException("无法删除其它用户资料");
        }
        materialMapper.deleteArticle(deleteArticleDTO.getArticleId());
    }

    @Override
    public void changeCompetence(ChangeCompetenceDTO changeCompetenceDTO) {
        if (changeCompetenceDTO.getCompetence().equals("仅查看")) {
            Integer status = materialMapper.getStatusById(changeCompetenceDTO.getArticleId());
            if (status < 2) materialMapper.changeCompetence(changeCompetenceDTO.getArticleId(), 0);
            if (status > 2) materialMapper.changeCompetence(changeCompetenceDTO.getArticleId(), 3);
        } else if (changeCompetenceDTO.getCompetence().equals("可下载")) {
            Integer status = materialMapper.getStatusById(changeCompetenceDTO.getArticleId());
            if (status < 2) materialMapper.changeCompetence(changeCompetenceDTO.getArticleId(), 1);
            if (status > 2) materialMapper.changeCompetence(changeCompetenceDTO.getArticleId(), 4);
        }
    }
}
