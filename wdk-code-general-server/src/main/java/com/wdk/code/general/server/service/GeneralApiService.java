package com.wdk.code.general.server.service;

import com.github.pagehelper.PageInfo;
import com.wdk.code.general.server.storage.entity.GeneralApi;
import com.wdk.code.general.server.web.args.GeneralApiArgs;
import com.wdk.code.general.server.web.vo.GeneralApiVo;
import com.wdk.general.core.common.model.PageParam;

import java.util.List;
import java.util.Map;

/**
 *API信息维护表
 * @author wdke
 * @date 2020/01/15
 */
public interface GeneralApiService{

	/**
	* 查询返回key为数据库字段的map
	* @param param ：查询条件Map,key为数据库字段，value为值
	* @return ：List<Map<String,Object>>
	* @author jack
	* @date 2020/01/15 18:12
	*/
	List<Map<String,Object>> selectListByMapReturnMap(Map<String,Object> param);

	/**
	* 根据map获取查询列表
	* @param param ：查询条件Map,key为java对应字段，value为值
	* @return ：List<GeneralApi
	* @author jack
	* @date 2020/01/15 18:12
	*/
	List<GeneralApi> selectListByMap(Map<String,Object> param);

	/**
	* 获取查询列表
	* @param param
	* @return ：List<GeneralApi
	* @author jack
	* @date 2020/01/15 18:12
	*/
	List<GeneralApiVo> list(GeneralApiArgs param);

	/**
	* 统计接口

	* @param param
	* @return Integer
	* @author jack
	* @date 2020/01/15 18:12
	*/
	Integer count(GeneralApiArgs param);

	/**
	* 根据主键查询数据
	*
	* @param id
	* @author jack
	* @date 2020/01/15 18:12
	*/
	GeneralApiVo selectByPrimaryKey(Integer id);

	/**
	* 分页查询
	* @param param：查询参数
	* @param pageParam ::分页参数
	* @return ：PageInfo<GeneralApi:分页结果集
	* @author jack
	* @date 2020/01/15 18:12
	*/
	PageInfo<GeneralApiVo> pageInfo(GeneralApiArgs param, PageParam pageParam);

	/**
	* 获取查询列表
	* @param param
	* @return ：int 数量
	* @author jack
	* @date 2020/01/15 18:12
	*/
	int insert(GeneralApi param);

	/**
	* 不为空新增
	* @param param
	* @return ：int 数量
	* @author jack
	* @date 2020/01/15 18:12
	*/
	int insertSelective(GeneralApiArgs param);

	/**
	* 批量新增
	* @param param
	* @return ：int 数量
	* @author jack
	* @date 2020/01/15 18:12
	*/
	int batchInsert(List<GeneralApi> param);

	/**
	* 根据主键更新全量
	* @param param
	* @return ：int 数量
	* @author jack
	* @date 2020/01/15 18:12
	*/
	int updateByPrimaryKey(GeneralApi param);

	/**
	* 根据主键更新全量
	* @param param
	* @return ：int 数量
	* @author jack
	* @date 2020/01/15 18:12
	*/
	int updateSelectiveByPrimaryKey(GeneralApiArgs param);

	/**
	* 存在即更新，不存在就插入
	* @param param
	* @author jack
	* @date 2020/01/15 18:12
	*/
	int batchInsertUpdate(List<GeneralApi> param);

	/**
	* 批量更新
	* @param param
	* @author jack
	* @date 2020/01/15 18:12
	*/
	int batchUpdate(List<GeneralApi> param);

	/**
	* 根据主键删除数据
	* @param id
	* @author jack
	* @date 2020/01/15 18:12
	*/
	int deleteByPrimaryKey(Integer id);

	/**
	* 根据查询条件删除数据
	* @param param
	* @author jack
	* @date 2020/01/15 18:12
	*/
	int deleteBySelect(GeneralApi param);



}