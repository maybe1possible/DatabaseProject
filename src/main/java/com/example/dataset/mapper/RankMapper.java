package com.example.dataset.mapper;

import com.example.dataset.VO.RankArticleVO;
import com.example.dataset.VO.RankUserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RankMapper {

    List<RankArticleVO> getArticleRank(int limit);

    List<RankUserVO> getUserRank(int limit);
}
