package com.wdk.general.core.storage.dao;

import com.wdk.general.core.storage.entity.ProjectMetadata;
import com.wdk.general.core.storage.entity.ProjectMetadataExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ProjectMetadataMapper {
    long countByExample(ProjectMetadataExample example);

    int deleteByExample(ProjectMetadataExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectMetadata record);

    int insertSelective(ProjectMetadata record);

    List<ProjectMetadata> selectByExampleWithRowbounds(ProjectMetadataExample example, RowBounds rowBounds);

    List<ProjectMetadata> selectByExample(ProjectMetadataExample example);

    ProjectMetadata selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectMetadata record, @Param("example") ProjectMetadataExample example);

    int updateByExample(@Param("record") ProjectMetadata record, @Param("example") ProjectMetadataExample example);

    int updateByPrimaryKeySelective(ProjectMetadata record);

    int updateByPrimaryKey(ProjectMetadata record);

    Long sumByExample(ProjectMetadataExample example);

    void batchInsert(@Param("items") List<ProjectMetadata> items);
}