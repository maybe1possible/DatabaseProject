package com.example.dataset.mapper;

import com.example.dataset.VO.SearchHistoryVO;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface SearchHistoryMapper {

    @Insert("insert into search_history (user_id, key_content, time) values (#{userId}, #{keyword}, #{now})")
    void addSearchHistory(int userId, String keyword, LocalDateTime now);

    @Select("select search_history_id as id, key_content as keyword, time from search_history where user_id=#{userId} order by time desc limit 10")
    List<SearchHistoryVO> getSearchHistoryByUserId(int userId);

    @Delete("delete from search_history where search_history_id=#{searchHistoryId}")
    void deleteSearchHistory(Integer searchHistoryId);

    @Delete("delete from search_history where user_id=#{userId}")
    void deleteAllSearchHistory(Integer userId);

    @Select("select search_history_id from search_history where key_content=#{keyword}")
    Integer getIdBykeyword(String keyword);

    @Update("update search_history set time=#{now} where search_history_id=#{id}")
    void updateSearchHistoryTime(Integer id, LocalDateTime now);
}
