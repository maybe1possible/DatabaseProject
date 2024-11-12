package com.example.dataset.mapper;

import com.example.dataset.VO.ViewHistoryVO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;

@Mapper
public interface ViewHistoryMapper {
    @Insert("insert into view_history (user_id, material_id, view_time) values (#{userId}, #{articleId}, #{now})")
    void addViewHistory(int userId, int articleId, LocalDateTime now);

    Page<ViewHistoryVO> getViewHistoryPageById(int userId);
}
