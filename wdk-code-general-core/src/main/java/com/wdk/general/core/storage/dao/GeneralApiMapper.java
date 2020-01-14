package com.wdk.general.core.storage.dao;

import com.wdk.general.core.storage.entity.GeneralApi;
import com.wdk.general.core.storage.entity.GeneralApiExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface GeneralApiMapper {
    long countByExample(GeneralApiExample example);

    int deleteByExample(GeneralApiExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GeneralApi record);

    int insertSelective(GeneralApi record);

    List<GeneralApi> selectByExampleWithRowbounds(GeneralApiExample example, RowBounds rowBounds);

    List<GeneralApi> selectByExample(GeneralApiExample example);

    GeneralApi selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GeneralApi record, @Param("example") GeneralApiExample example);

    int updateByExample(@Param("record") GeneralApi record, @Param("example") GeneralApiExample example);

    int updateByPrimaryKeySelective(GeneralApi record);

    int updateByPrimaryKey(GeneralApi record);

    Long sumByExample(GeneralApiExample example);

    void batchInsert(@Param("items") List<GeneralApi> items);
}