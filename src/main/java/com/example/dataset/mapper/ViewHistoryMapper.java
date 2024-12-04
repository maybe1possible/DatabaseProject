package com.example.dataset.mapper;

import com.example.dataset.VO.ViewHistoryVO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;

@Mapper
public interface ViewHistoryMapper {
    @Insert("insert into view_history (user_id, material_id, view_time) values (#{userId}, #{articleId}, #{now})")
    void addViewHistory(int userId, int articleId, LocalDateTime now);

    Page<ViewHistoryVO> getViewHistoryPageById(int userId);

    @Delete("delete from view_history where view_history_id=#{viewHistoryId}")
    void deleteViewHistory(Integer viewHistoryId);

    @Select("select view_history_id from  view_history where user_id=#{userId} and material_id=#{articleId}")
    Integer getIdByUserAndArticleId(Integer userId, Integer articleId);

    @Update("update view_history set view_time=#{now} where view_history_id=#{id}")
    void updateViewHistoryTime(Integer id, LocalDateTime now);
}
