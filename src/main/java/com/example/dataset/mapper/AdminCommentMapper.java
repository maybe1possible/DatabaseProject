package com.example.dataset.mapper;

import com.example.dataset.VO.CommentWithoutAuditListVO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface AdminCommentMapper {

    @Select("select c.material_id as id, m.title, c.content, c.comment_time, c.user_id from comments c join materials m on m.material_id = c.material_id where c.state=0 order by comment_time")
    Page<CommentWithoutAuditListVO> getCommentWithoutAudit();

    @Update("update comments set state=#{i} where comment_id=#{id}")
    void setCommentStatus(Integer id, int i);
}
