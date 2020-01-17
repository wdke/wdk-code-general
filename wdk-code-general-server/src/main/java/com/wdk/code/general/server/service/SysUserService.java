package com.wdk.code.general.server.service;

import com.github.pagehelper.PageInfo;
import com.wdk.code.general.server.storage.entity.SysUser;
import com.wdk.code.general.server.web.args.SysUserArgs;
import com.wdk.code.general.server.web.vo.SysUserVo;
import com.wdk.general.core.common.model.PageParam;

import java.util.List;
import java.util.Map;

/**
 *用户管理
 * @author wdke
 * @date 2020/01/15
 */
public interface SysUserService{

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
	* @return ：List<SysUser
	* @author jack
	* @date 2020/01/15 18:12
	*/
	List<SysUser> selectListByMap(Map<String,Object> param);

	/**
	* 获取查询列表
	* @param param
	* @return ：List<SysUser
	* @author jack
	* @date 2020/01/15 18:12
	*/
	List<SysUserVo> list(SysUserArgs param);

	/**
	* 统计接口

	* @param param
	* @return Integer
	* @author jack
	* @date 2020/01/15 18:12
	*/
	Integer count(SysUserArgs param);

	/**
	* 根据主键查询数据
	*
	* @param id
	* @author jack
	* @date 2020/01/15 18:12
	*/
	SysUserVo selectByPrimaryKey(Integer id);

	/**
	* 分页查询
	* @param param：查询参数
	* @param pageParam ::分页参数
	* @return ：PageInfo<SysUser:分页结果集
	* @author jack
	* @date 2020/01/15 18:12
	*/
	PageInfo<SysUserVo> pageInfo(SysUserArgs param, PageParam pageParam);

	/**
	* 获取查询列表
	* @param param
	* @return ：int 数量
	* @author jack
	* @date 2020/01/15 18:12
	*/
	int insert(SysUser param);

	/**
	* 不为空新增
	* @param param
	* @return ：int 数量
	* @author jack
	* @date 2020/01/15 18:12
	*/
	int insertSelective(SysUserArgs param);

	/**
	* 批量新增
	* @param param
	* @return ：int 数量
	* @author jack
	* @date 2020/01/15 18:12
	*/
	int batchInsert(List<SysUser> param);

	/**
	* 根据主键更新全量
	* @param param
	* @return ：int 数量
	* @author jack
	* @date 2020/01/15 18:12
	*/
	int updateByPrimaryKey(SysUser param);

	/**
	* 根据主键更新全量
	* @param param
	* @return ：int 数量
	* @author jack
	* @date 2020/01/15 18:12
	*/
	int updateSelectiveByPrimaryKey(SysUserArgs param);

	/**
	* 存在即更新，不存在就插入
	* @param param
	* @author jack
	* @date 2020/01/15 18:12
	*/
	int batchInsertUpdate(List<SysUser> param);

	/**
	* 批量更新
	* @param param
	* @author jack
	* @date 2020/01/15 18:12
	*/
	int batchUpdate(List<SysUser> param);

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
	int deleteBySelect(SysUser param);



}