package com.example.dataset.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.example.dataset.DTO.MaterialInfoDTO;
import com.example.dataset.DTO.MaterialPageDTO;
import com.example.dataset.VO.MaterialListVO;
import com.example.dataset.config.AliOssProperties;
import com.example.dataset.entity.Material;
import com.example.dataset.mapper.MaterialMapper;
import com.example.dataset.mapper.MaterialTagMapper;
import com.example.dataset.mapper.TagMapper;
import com.example.dataset.service.MaterialService;
import com.example.dataset.utils.PageResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

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

        return ossClient.generatePresignedUrl(aliOssProperties.getBucketName(), filename, expiration).toString();
    }

    @Override
    public PageResult pageSearchByKeyword(MaterialPageDTO materialPageDTO) {
        PageHelper.startPage(materialPageDTO.getPage(), materialPageDTO.getPageSize());
        Page<MaterialListVO> page = materialMapper.pageSearchByKeyword(materialPageDTO);
        log.info(String.valueOf(page.getTotal()));
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public PageResult pageSearchByNav(MaterialPageDTO materialPageDTO) {
        PageHelper.startPage(materialPageDTO.getPage(), materialPageDTO.getPageSize());
        //TODO: implement recommend
        Page<MaterialListVO> page = materialMapper.pageSearchByOfficial(materialPageDTO);
        return new PageResult(page.getTotal(), page.getResult());

    }

    @Override
    public PageResult pageSearchById(Integer userId, Integer page, Integer pageSize, String sort) {
        PageHelper.startPage(page, pageSize);
        Page<MaterialListVO> pages = materialMapper.pageSearchById(userId, sort);
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
}
