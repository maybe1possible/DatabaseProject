package com.example.dataset.mapper;

import com.example.dataset.VO.CommentInfoVO;
import com.example.dataset.VO.MyCommentInfoVO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;

@Mapper
public interface CommentMapper {

    @Insert("insert into comments (material_id, user_id, content, comment_time, state) values (#{articleId}, #{userId}, #{commentContent}, #{now}, 0)")
    void postComment(Integer articleId, String commentContent, Integer userId, LocalDateTime now);

    @Select("select comment_id, u.user_id, u.nickname, u.avatar, content, comment_time as time,  (SELECT COUNT(*) FROM comments WHERE material_id = #{articleId}) as num  from comments left join users u on comments.user_id=u.user_id where comments.material_id=#{articleId} and comments.state=1 order by comment_time desc")
    @Results(id = "commentResult", value = {
            @Result(property = "commentId", column = "comment_id"),
            @Result(property = "content", column = "content"),
            @Result(property = "time", column = "comment_time"),
            @Result(property = "author.id", column = "user_id"),
            @Result(property = "author.name", column = "nickname"),
            @Result(property = "author.avatar", column = "avatar"),
            @Result(property = "total", column = "num"),

    })
    Page<CommentInfoVO> getCommentPageById(int articleId);

    @Select("select c.comment_id, m.material_id, m.title, m.file_type, c.content, c.comment_time, c.state from comments c left join materials m on c.material_id=m.material_id where c.user_id=#{id} order by c.comment_time desc")
    Page<MyCommentInfoVO> getMyCommentById(int id);

    @Delete("delete from comments where comment_id=#{commentId}")
    void deleteComment(Integer commentId);
}
