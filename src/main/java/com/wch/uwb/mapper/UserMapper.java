package com.wch.uwb.mapper;

import com.wch.uwb.entity.UserEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;
//id  login userName author passwd sex resume photo registerTime lastLoginTime address brith bolg emotion sexOrientation qq msn mail profession
public interface UserMapper {
    @Select("SELECT * FROM user")
    List<UserEntity> getAll();

    @Select("SELECT * FROM user WHERE id = #{id}")
    UserEntity getOne(int id);

    @Select("SELECT * FROM user WHERE login = #{login}")
    UserEntity getOneByLogin(String login);
    @Select("SELECT * FROM user WHERE author = #{author}")
    List<UserEntity> getOneByAuthor(int author);

    @Insert("INSERT INTO user(" +
            "login,userName,author,passwd,sex,resume,question,answer,photo,registerTime) " +
            "VALUES(" +
            "#{login},#{userName},#{author},#{passwd},#{sex},#{resume},#{question},#{answer},#{photo},#{registerTime})")
    void insert(UserEntity user);
//login userName author passwd sex resume photo registerTime lastLoginTime address
// brith bolg emotion sexOrientation qq msn mail profession
    @Update("UPDATE user SET login=#{login},userName=#{userName},author=#{author},photo=#{photo}" +
            ",passwd=#{passwd},sex=#{sex},resume=#{resume},registerTime=#{registerTime},free=#{free}" +
            " WHERE id =#{id}")
    void update(UserEntity user);

    @Delete("DELETE FROM user WHERE id =#{id}")
    void delete(int id);
}
