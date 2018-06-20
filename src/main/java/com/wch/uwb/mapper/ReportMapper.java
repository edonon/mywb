package com.wch.uwb.mapper;

import com.wch.uwb.entity.ReportEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ReportMapper {
    @Insert("INSERT INTO report(" +
            "wId,time) " +
            "VALUES(" +
            "#{wId},#{time})")
    void insert(ReportEntity ReportEntity);

    @Delete("DELETE FROM report WHERE id =#{id}")
    void delete(int id);
    @Delete("DELETE FROM report WHERE wId =#{wId}")
    void deleteBYWId(int wId);
    @Select("SELECT COUNT(*) FROM report")
    int getCNT();
    @Select("SELECT * FROM report")
    List<ReportEntity> getALL();
}
