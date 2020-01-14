package com.wdk.general.core.storage.dao;

import com.wdk.general.core.storage.entity.DbMessages;
import com.wdk.general.core.storage.entity.DbMessagesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

@Repository
public interface DbMessagesMapper {
    long countByExample(DbMessagesExample example);

    int deleteByExample(DbMessagesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DbMessages record);

    int insertSelective(DbMessages record);

    List<DbMessages> selectByExampleWithRowbounds(DbMessagesExample example, RowBounds rowBounds);

    List<DbMessages> selectByExample(DbMessagesExample example);

    DbMessages selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DbMessages record, @Param("example") DbMessagesExample example);

    int updateByExample(@Param("record") DbMessages record, @Param("example") DbMessagesExample example);

    int updateByPrimaryKeySelective(DbMessages record);

    int updateByPrimaryKey(DbMessages record);

    Long sumByExample(DbMessagesExample example);

    void batchInsert(@Param("items") List<DbMessages> items);
}