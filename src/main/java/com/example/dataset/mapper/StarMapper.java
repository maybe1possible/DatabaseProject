package com.example.dataset.mapper;

import com.example.dataset.VO.StarAllVO;
import com.example.dataset.VO.StarInfoVO;
import com.example.dataset.entity.StarDirectory;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface StarMapper {

    @Insert("insert into star_directory (directory_name, description, user_id) values (#{directoryName}, #{description}, #{userId})")
    void createStarDirectory(StarDirectory starDirectory);

    @Insert("insert into stars (material_id, user_id, star_time, directory_id) values (#{article_id}, #{user_id}, #{now}, #{favorites_id})")
    void addStar(Integer favorites_id,Integer article_id , Integer user_id, LocalDateTime now);

    @Select("select user_id from star_directory where directory_id=#{directory_id}")
    Integer getUserIdByStarId(int directory_id);

    @Delete("delete from stars where directory_id=#{favoritesId} and material_id=#{articleId}")
    void cancelStar(int favoritesId, int articleId);

    @Select("select d.directory_id, d.directory_name, (select COUNT(*) from stars where directory_id=d.directory_id) as article_size from star_directory d where user_id=#{user_id}")
    List<StarAllVO> getStarDirectorysByUserId(Integer userId);

    @Select("select m.material_id as articleId, m.title, m.description from stars s left join materials m on m.material_id=s.material_id where s.directory_id=#{favoritesId}")
    Page<StarInfoVO> getMaterialByDirId(Integer favoritesId);
}
