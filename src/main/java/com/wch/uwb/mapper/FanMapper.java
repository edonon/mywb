package com.wch.uwb.mapper;

import com.wch.uwb.entity.FanEntity;
import com.wch.uwb.entity.UserEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface FanMapper {
    @Select("SELECT * FROM fan WHERE uid = #{uid}")
    List<FanEntity> getByUid(int uid);

    @Select("SELECT * FROM fan WHERE fanId = #{fanId}")
    List<FanEntity> getByfanId(int fanId);

    @Insert("INSERT INTO fan(" +
            "uId,fanId,time) " +
            "VALUES(" +
            "#{uId},#{fanId},#{time})")
    void insert(FanEntity fanEntity);

    @Delete("DELETE FROM fan WHERE uId =#{uId} and fanId =#{fanId}")
    void delete(@Param("uId")int uId, @Param("fanId")int fanId);
}
