package com.example.dataset.mapper;


import com.example.dataset.DTO.MaterialInfoDTO;
import com.example.dataset.DTO.MaterialPageDTO;
import com.example.dataset.VO.MaterialListVO;
import com.example.dataset.VO.MyMaterialListVO;
import com.example.dataset.entity.Material;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MaterialMapper {

    Integer upload(Material material);

    @Select("select content_path from materials where material_id = #{materialId}")
    String getFilename(int materialId);

    Page<MaterialListVO> pageSearchByKeyword(MaterialPageDTO materialPageDTO);

//    Page<MaterialListVO> pageSearchByKeywordAndHeat(MaterialPageDTO materialPageDTO);

    Page<MaterialListVO> pageSearchByOfficial(MaterialPageDTO materialPageDTO);

    Page<MyMaterialListVO> pageSearchById(int userId, String sort);

    @Select("select * from materials where material_id = #{materialId}")
    Material getMaterialById(int materialId);

    MaterialInfoDTO getMaterialInfoById(int materialId);

    Page<MaterialListVO> pageSearchByRecommend(MaterialPageDTO materialPageDTO);

    @Select("select user_id from materials where material_id=#{articleId}")
    Integer getUserId(Integer articleId);

    @Update("update materials set state=5 where material_id=#{articleId}")
    void deleteArticle(Integer articleId);

    @Select("select state from materials where material_id=#{articleId}")
    Integer getStatusById(Integer articleId);

    @Update("update materials set state=#{i} where material_id=#{articleId}")
    void changeCompetence(Integer articleId, int i);

    Page<MaterialListVO> pageSearchAll(MaterialPageDTO materialPageDTO);
}
