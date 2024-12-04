package com.example.dataset.mapper;

import com.example.dataset.VO.DownloadInfoVO;
import com.example.dataset.entity.Download;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DownloadMapper {

    @Select("select d.download_id as id, m.title, m.file_type, m.size as fileSize,d.local_path, d.download_time from downloads d left join materials m on d.material_id=m.material_id where d.user_id=#{userId} order by d.download_time desc")
    Page<DownloadInfoVO> getDownloadInfoById(int userId);

    @Insert("insert into downloads (material_id, user_id, local_path, download_time) values (#{materialId}, #{userId}, #{localPath}, #{downloadTime})")
    void uploadDownloads(Download download);

    @Delete("delete from downloads where download_id=#{downloadId}")
    void deleteDownload(Integer downloadId);

    @Select("select download_id from downloads where user_id=#{userId} and material_id=#{materialId}")
    List<Integer> getIfDownloaded(Integer userId, Integer materialId);
}
