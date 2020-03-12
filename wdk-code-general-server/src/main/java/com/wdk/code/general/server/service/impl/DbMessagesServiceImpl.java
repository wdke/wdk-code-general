package com.wdk.code.general.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wdk.code.general.server.storage.entity.DbMessages;
import com.wdk.code.general.server.web.args.DbMessagesArgs;
import com.wdk.code.general.server.web.vo.DbMessagesVo;
import com.wdk.code.general.server.service.DbMessagesService;
import com.wdk.code.general.server.storage.dao.DbMessagesMapper;
import com.alibaba.fastjson.JSON;
import com.wdk.general.core.common.model.PageParam;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据源信息
 *
 * @author wdke
 * @date 2020/01/15
 */
@Service
public class DbMessagesServiceImpl implements DbMessagesService {

    private static Logger logger = LoggerFactory.getLogger(DbMessagesServiceImpl.class);

    @Autowired
    private DbMessagesMapper dbMessagesMapper;


    /**
     * 查询返回key为数据库字段的map
     *
     * @param param ：查询条件Map,key为数据库字段，value为值
     * @return ：List<Map<String,Object>>
     * @author jack
     * @date 2020/01/15 18:12
     */
    @Override
    public List<Map<String, Object>> selectListByMapReturnMap(Map<String, Object> param) {

        List<Map<String, Object>> result = dbMessagesMapper.selectListByMapReturnMap(param);
        return result;
    }

    /**
     * 根据map获取查询列表
     *
     * @param param ：查询条件Map,key为java对应字段，value为值
     * @return ：List<DbMessages
     * @author jack
     * @date 2020/01/15 18:12
     */
    @Override
    public List<DbMessages> selectListByMap(Map<String, Object> param) {

        List<DbMessages> result = dbMessagesMapper.selectListByMap(param);
        return result;
    }

    /**
     * 获取查询列表
     *
     * @param param
     * @return ：List<DbMessagesVo>
     * @author jack
     * @date 2020/01/15 18:12
     */
    @Override
    public List<DbMessagesVo> list(DbMessagesArgs param) {

        //参数类型转化
        DbMessages dbMessages = new DbMessages();

        BeanUtils.copyProperties(param, dbMessages);

        //访问数据库
        List<DbMessages> data = dbMessagesMapper.list(dbMessages);

        //类型转化
        List<DbMessagesVo> result = JSON.parseArray(JSON.toJSONString(data), DbMessagesVo.class);

        return result;
    }

    /**
     * 统计接口
     *
     * @param param
     * @return Integer
     * @author jack
     * @date 2020/01/15 18:12
     */
    @Override
    public Integer count(DbMessagesArgs param) {

        //参数类型转化
        DbMessages dbMessages = new DbMessages();

        BeanUtils.copyProperties(param, dbMessages);

        Integer result = dbMessagesMapper.count(dbMessages);

        return result;
    }

    /**
     * 根据主键查询数据
     *
     * @param id
     * @author jack
     * @date 2020/01/15 18:12
     */
    @Override
    public DbMessagesVo selectByPrimaryKey(Integer id) {

        //查询数据库
        DbMessages data = dbMessagesMapper.selectByPrimaryKey(id);

        //判断是否查询到数据
        if (null == data) {
            return null;
        }        //类型转化
        DbMessagesVo result = new DbMessagesVo();
        BeanUtils.copyProperties(data, result);

        return result;
    }

    /**
     * 分页查询
     *
     * @param param：查询参数
     * @param pageParam  ::分页参数
     * @return ：PageInfo<DbMessages:分页结果集
     * @author jack
     * @date 2020/01/15 18:12
     */
    @Override
    public PageInfo<DbMessagesVo> pageInfo(DbMessagesArgs param, PageParam pageParam) {

        //参数类型转化
        DbMessages dbMessages = new DbMessages();
        BeanUtils.copyProperties(param, dbMessages);

        PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());

        //分页查询
        List<DbMessages> data = dbMessagesMapper.list(dbMessages);

        //类型转化
        List<DbMessagesVo> result = JSON.parseArray(JSON.toJSONString(data), DbMessagesVo.class);

        PageInfo pageInfo = new PageInfo(data);
        pageInfo.setList(result);

        return pageInfo;
    }

    /**
     * 获取查询列表
     *
     * @param param
     * @return ：int 数量
     * @author jack
     * @date 2020/01/15 18:12
     */
    @Override
    public int insert(DbMessages param) {

        int result = dbMessagesMapper.insert(param);
        return result;
    }

    /**
     * 不为空新增
     *
     * @param param
     * @return ：int 数量
     * @author jack
     * @date 2020/01/15 18:12
     */
    @Override
    public int insertSelective(DbMessagesArgs param) {
        //校验是否存在
        Map<String, Object> map = new HashMap<>();
        map.put("userId", param.getUserId());
        map.put("dbName", param.getDbName());
        map.put("dbType", param.getDbType());
        List<DbMessages> checkData = dbMessagesMapper.selectListByMap(map);
        if (!checkData.isEmpty()) {
            return 0;
        }
        //参数类型转化
        DbMessages dbMessages = new DbMessages();
        BeanUtils.copyProperties(param, dbMessages);


        int result = dbMessagesMapper.insertSelective(dbMessages);

        return result;
    }

    /**
     * 批量新增
     *
     * @param param
     * @return ：int 数量
     * @author jack
     * @date 2020/01/15 18:12
     */
    @Override
    public int batchInsert(List<DbMessages> param) {

        int result = dbMessagesMapper.batchInsert(param);
        return result;
    }

    /**
     * 根据主键更新全量
     *
     * @param param
     * @return ：int 数量
     * @author jack
     * @date 2020/01/15 18:12
     */
    @Override
    public int updateByPrimaryKey(DbMessages param) {

        int result = dbMessagesMapper.updateByPrimaryKey(param);
        return result;
    }

    /**
     * 根据主键更新全量
     *
     * @param param
     * @return ：int 数量
     * @author jack
     * @date 2020/01/15 18:12
     */
    @Override
    public int updateSelectiveByPrimaryKey(DbMessagesArgs param) {

        //参数类型转化
        DbMessages dbMessages = new DbMessages();
        BeanUtils.copyProperties(param, dbMessages);

        int result = dbMessagesMapper.updateSelectiveByPrimaryKey(dbMessages);

        return result;
    }

    /**
     * 存在即更新，不存在就插入
     *
     * @param param
     * @author jack
     * @date 2020/01/15 18:12
     */
    @Override
    public int batchInsertUpdate(List<DbMessages> param) {

        int result = dbMessagesMapper.batchInsertUpdate(param);
        return result;
    }

    /**
     * 批量更新入
     *
     * @param param
     * @author jack
     * @date 2020/01/15 18:12
     */
    @Override
    public int batchUpdate(List<DbMessages> param) {

        int result = dbMessagesMapper.batchUpdate(param);
        return result;
    }

    /**
     * 根据主键删除数据
     *
     * @param id
     * @author jack
     * @date 2020/01/15 18:12
     */
    @Override
    public int deleteByPrimaryKey(Integer id) {

        int result = dbMessagesMapper.deleteByPrimaryKey(id);
        return result;
    }

    /**
     * 根据查询删除数据
     *
     * @param param
     * @author jack
     * @date 2020/01/15 18:12
     */
    @Override
    public int deleteBySelect(DbMessages param) {

        int result = dbMessagesMapper.deleteBySelect(param);
        return result;
    }


}