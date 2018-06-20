package com.wch.uwb.mapper;

import com.wch.uwb.entity.CommentEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface CommentMapper {
    @Select("SELECT * FROM comment")
    List<CommentEntity> getAll();

    @Select("SELECT * FROM comment WHERE id = #{id}")
    CommentEntity getOne(int id);

    @Select("SELECT * FROM comment WHERE wId = #{wId}")
    List<CommentEntity> getOneByWID(int wId);

    @Insert("INSERT INTO comment(wId,uId,discuss,time)VALUES(" +
            "#{wId},#{uId},#{discuss},#{time})")
    void insert(CommentEntity Comment);

    @Update("UPDATE comment SET wId=#{wId},uId=#{uId},discuss=#{discuss},time=#{time} WHERE id =#{id}")
    void update(CommentEntity Comment);

    @Delete("DELETE FROM comment WHERE id =#{id}")
    void delete(int id);
}
