package com.wdk.general.core.storage.dao;

import com.wdk.general.core.storage.entity.NideshopOrder;
import com.wdk.general.core.storage.entity.NideshopOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface NideshopOrderMapper {
    long countByExample(NideshopOrderExample example);

    int deleteByExample(NideshopOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(NideshopOrder record);

    int insertSelective(NideshopOrder record);

    List<NideshopOrder> selectByExampleWithRowbounds(NideshopOrderExample example, RowBounds rowBounds);

    List<NideshopOrder> selectByExample(NideshopOrderExample example);

    NideshopOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") NideshopOrder record, @Param("example") NideshopOrderExample example);

    int updateByExample(@Param("record") NideshopOrder record, @Param("example") NideshopOrderExample example);

    int updateByPrimaryKeySelective(NideshopOrder record);

    int updateByPrimaryKey(NideshopOrder record);

    Long sumByExample(NideshopOrderExample example);

    void batchInsert(@Param("items") List<NideshopOrder> items);
}