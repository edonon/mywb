package com.wch.uwb.mapper;

import com.wch.uwb.entity.InformEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface InformMapper {
    @Select("SELECT * FROM inform WHERE uid = #{uid}")
    InformEntity getByUid(int uid);

    @Insert("INSERT INTO inform(" +
            "id,uid,commentCnt,chatCnt) " +
            "VALUES(" +
            "#{id},#{uid},#{commentCnt},#{chatCnt})")
    void insert(InformEntity informEntity);

    @Update("UPDATE inform SET uid=#{uid},commentCnt=#{commentCnt},chatCnt=#{chatCnt}" +
            " WHERE id =#{id}")
    void update(InformEntity informEntity);
}
