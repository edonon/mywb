package com.wch.uwb.mapper;

import com.wch.uwb.entity.Chat;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ChatMapper {
    @Select("SELECT * FROM Chat WHERE wIdFri = #{wIdFri} and wIdSec = #{wIdSec}")
    List<Chat> getOneByDoubleID(@Param("wIdFri")int wIdFri, @Param("wIdSec")int wIdSec);

    @Insert("INSERT INTO Chat(wIdFri,wIdSec,direction,discuss,time)VALUES(" +
            "#{wIdFri},#{wIdSec},#{direction},#{discuss},#{time})")
    void insert(Chat chat);
}
