package com.example.dataset.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MaterialTagMapper {

    void insertTags(int materialId, Integer[] tagIds);
}
