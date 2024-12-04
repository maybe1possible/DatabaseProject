package com.example.dataset.service.impl;

import com.example.dataset.DTO.StarDirectoryCreateDTO;
import com.example.dataset.DTO.StarMaterialDTO;
import com.example.dataset.VO.StarAllVO;
import com.example.dataset.VO.StarInfoVO;
import com.example.dataset.entity.StarDirectory;
import com.example.dataset.exception.DuplicateDirectoryException;
import com.example.dataset.mapper.StarMapper;
import com.example.dataset.service.StarService;
import com.example.dataset.utils.PageResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StarServiceimpl implements StarService {

    @Autowired
    private StarMapper starMapper;

    @Override
    public void makeStarDictory(StarDirectoryCreateDTO starDirectoryCreateDTO) throws DuplicateDirectoryException {
        try {
            StarDirectory starDirectory = new StarDirectory();
            starDirectory.setDirectoryName(starDirectoryCreateDTO.getFolder_name());
            starDirectory.setDescription(starDirectoryCreateDTO.getDescription());
            starDirectory.setUserId(starDirectoryCreateDTO.getUser_id());
            starDirectory.setTime(LocalDateTime.now());
            starMapper.createStarDirectory(starDirectory);
        } catch (RuntimeException e) {
            throw new DuplicateDirectoryException("文件夹重复");
        }
    }

    @Override
    @Transactional
    public void postStar(StarMaterialDTO starMaterialDTO) {
        if (starMaterialDTO.getType().equals("star")) {
            Integer user_id = starMapper.getUserIdByStarId(starMaterialDTO.getFavorites_id());
            starMapper.addStar(starMaterialDTO.getFavorites_id(), starMaterialDTO.getArticle_id(), user_id, LocalDateTime.now());
        } else if (starMaterialDTO.getType().equals("cancel")) {
            starMapper.cancelStar(starMaterialDTO.getUser_id(), starMaterialDTO.getArticle_id());
        } else {
            throw new IllegalArgumentException("参数错误");
        }

    }

    @Override
    public PageResult getStars(Integer favorites_id, Integer user_id, Integer pageNumber, Integer pageSize) {
        if (favorites_id == 0) {
//            PageHelper.startPage(starPageDTO.getPageNumber(), starPageDTO.getPageSize());
//            Page<StarAllVO> page = starMapper.getStarDirectorysByUserId(starPageDTO.getUser_id());
            List<StarAllVO> starDirectorysByUserId = starMapper.getStarDirectorysByUserId(user_id);
            return new PageResult(starDirectorysByUserId.size(), starDirectorysByUserId);
        }
        PageHelper.startPage(pageNumber, pageSize);
        Page<StarInfoVO> page = starMapper.getMaterialByDirId(favorites_id);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public Integer getIfStared(Integer userId, Integer materialId) {
        List<Integer> ids = starMapper.getIfStared(userId, materialId);
        return ids.isEmpty() ? 0 : 1;
    }

    @Override
    public void deleteFavorites(Integer id) {
        starMapper.deleteFavorites(id);
    }
}
