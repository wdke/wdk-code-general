package com.wdk.general.core.storage.dao;

import com.wdk.general.core.storage.entity.Nideshop_cart;
import com.wdk.general.core.storage.entity.Nideshop_cartExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface Nideshop_cartMapper {
    long countByExample(Nideshop_cartExample example);

    int deleteByExample(Nideshop_cartExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Nideshop_cart record);

    int insertSelective(Nideshop_cart record);

    List<Nideshop_cart> selectByExampleWithBLOBsWithRowbounds(Nideshop_cartExample example, RowBounds rowBounds);

    List<Nideshop_cart> selectByExampleWithBLOBs(Nideshop_cartExample example);

    List<Nideshop_cart> selectByExampleWithRowbounds(Nideshop_cartExample example, RowBounds rowBounds);

    List<Nideshop_cart> selectByExample(Nideshop_cartExample example);

    Nideshop_cart selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Nideshop_cart record, @Param("example") Nideshop_cartExample example);

    int updateByExampleWithBLOBs(@Param("record") Nideshop_cart record, @Param("example") Nideshop_cartExample example);

    int updateByExample(@Param("record") Nideshop_cart record, @Param("example") Nideshop_cartExample example);

    int updateByPrimaryKeySelective(Nideshop_cart record);

    int updateByPrimaryKeyWithBLOBs(Nideshop_cart record);

    int updateByPrimaryKey(Nideshop_cart record);

    Long sumByExample(Nideshop_cartExample example);

    void batchInsert(@Param("items") List<Nideshop_cart> items);
}