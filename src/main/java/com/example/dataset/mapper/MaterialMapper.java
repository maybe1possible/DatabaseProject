package com.example.dataset.mapper;


import com.example.dataset.DTO.MaterialInfoDTO;
import com.example.dataset.DTO.MaterialPageDTO;
import com.example.dataset.VO.MaterialListVO;
import com.example.dataset.entity.Material;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MaterialMapper {

    Integer upload(Material material);

    @Select("select content_path from materials where material_id = #{materialId}")
    String getFilename(int materialId);

    Page<MaterialListVO> pageSearchByKeyword(MaterialPageDTO materialPageDTO);

//    Page<MaterialListVO> pageSearchByKeywordAndHeat(MaterialPageDTO materialPageDTO);

    Page<MaterialListVO> pageSearchByOfficial(MaterialPageDTO materialPageDTO);

    Page<MaterialListVO> pageSearchById(int userId, String sort);

    @Select("select * from materials where material_id = #{materialId}")
    Material getMaterialById(int materialId);

    MaterialInfoDTO getMaterialInfoById(int materialId);
}
