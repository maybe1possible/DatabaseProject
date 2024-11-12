package com.example.dataset.mapper;

import com.example.dataset.VO.SearchHistoryVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface SearchHistoryMapper {

    @Insert("insert into search_history (user_id, key_content, time) values (#{userId}, #{keyword}, #{now})")
    void addSearchHistory(int userId, String keyword, LocalDateTime now);

    @Select("select key_content as keyword, time from search_history where user_id=#{userId}")
    List<SearchHistoryVO> getSearchHistoryByUserId(int userId);
}
