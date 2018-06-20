package com.wch.uwb.mapper;

import com.wch.uwb.entity.Chatlist;
import com.wch.uwb.entity.CommentEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ChatlistMapper {
    @Select("SELECT * FROM chatlist WHERE seuid = #{seuid}")
    List<Chatlist> getOneBySeuid(int seuid);
    @Select("SELECT * FROM chatlist WHERE uid = #{uid}")
    List<Chatlist> getOneByUid(int uid);
    @Insert("INSERT INTO chatlist(uid,seuid)VALUES(" +
            "#{uid},#{seuid})")
    void insert(Chatlist chatlist);
    @Select("SELECT COUNT(*) FROM chatlist  WHERE uid =#{uid}")
    int getCNT(int uid);
    @Delete("DELETE FROM comment WHERE uid =#{uid}")
    void delete(int uid);
}
