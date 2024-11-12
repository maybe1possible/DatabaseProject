package com.example.dataset.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TagMapper {

    @Select("select tag_id from tag where tag_name = #{tag_name}")
    Integer getTagId(String tag);

    @Insert("insert into tag (tag_name) values(#{tag})")
    void insert(String tag);

    @Select("select tag_name from material_tag left join tag on material_tag.tag_id = tag.tag_id where material_id = #{materialId}")
    List<String> getTagsByMaterialId(int materialId);
}
