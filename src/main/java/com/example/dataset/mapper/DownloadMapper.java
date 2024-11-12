package com.example.dataset.mapper;

import com.example.dataset.VO.DownloadInfoVO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DownloadMapper {

    @Select("select m.title, m.file_type, m.size as fileSize, d.download_time from downloads d left join materials m on d.material_id=m.material_id where d.user_id=#{userId} order by d.download_time desc")
    Page<DownloadInfoVO> getDownloadInfoById(int userId);
}
