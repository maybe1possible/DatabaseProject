package com.example.dataset.mapper;

import com.example.dataset.VO.ArticleWithoutAuditListVO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface AdminArticleMapper {

    @Select("select material_id as id, title, description, publish_time as uploadTime, content_path as url, user_id as authorId from materials where state > 2 order by publish_time")
    Page<ArticleWithoutAuditListVO> getArticleWithoutAudit();

    void setArticleStatus(Integer id, Integer pass);

    @Delete("delete from materials where material_id=#{articleId}")
    void deleteArticle(Integer articleId);
}
