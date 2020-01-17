package com.wdk.code.general.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wdk.code.general.server.service.SysUserService;
import com.wdk.code.general.server.storage.dao.SysUserMapper;
import com.wdk.code.general.server.storage.entity.SysUser;
import com.wdk.code.general.server.web.args.SysUserArgs;
import com.wdk.code.general.server.web.vo.SysUserVo;
import com.wdk.general.core.common.model.PageParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 *用户管理
 * @author wdke
 * @date 2020/01/15
 */
@Service
public class SysUserServiceImpl implements SysUserService{

	private static Logger logger=LoggerFactory.getLogger(SysUserServiceImpl.class);

	@Autowired
	private SysUserMapper sysUserMapper;



	/**
	* 查询返回key为数据库字段的map
	* @param param ：查询条件Map,key为数据库字段，value为值
	* @return ：List<Map<String,Object>>
	* @author jack
	* @date 2020/01/15 18:12
	*/
	@Override
	public List<Map<String,Object>> selectListByMapReturnMap(Map<String,Object> param){

		List<Map<String,Object>> result=sysUserMapper.selectListByMapReturnMap(param);
		return result;
	}

	/**
	* 根据map获取查询列表
	* @param param ：查询条件Map,key为java对应字段，value为值
	* @return ：List<SysUser
	* @author jack
	* @date 2020/01/15 18:12
	*/
	@Override
	public List<SysUser> selectListByMap(Map<String,Object> param){

		List<SysUser> result=sysUserMapper.selectListByMap(param);
		return result;
	}

	/**
	* 获取查询列表
	* @param param
	* @return ：List<SysUserVo>
	* @author jack
	* @date 2020/01/15 18:12
	*/
	@Override
	public List<SysUserVo> list(SysUserArgs param){

		//参数类型转化
		SysUser sysUser = new SysUser();

		BeanUtils.copyProperties(param,sysUser);

		//访问数据库
		List<SysUser> data = sysUserMapper.list(sysUser);

		//类型转化
		List<SysUserVo> result = JSON.parseArray(JSON.toJSONString(data), SysUserVo.class);

		return result;
	}

	/**
	* 统计接口

	* @param param
	* @return Integer
	* @author jack
	* @date 2020/01/15 18:12
	*/
	@Override
	public Integer count(SysUserArgs param){

		//参数类型转化
		SysUser sysUser = new SysUser();

		BeanUtils.copyProperties(param,sysUser);

		Integer result=sysUserMapper.count(sysUser);

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
	public SysUserVo selectByPrimaryKey(Integer id){

		//查询数据库
		SysUser data = sysUserMapper.selectByPrimaryKey(id);

		//判断是否查询到数据
		if (null == data) {
			return null;
		}		//类型转化
		SysUserVo result = new SysUserVo();
		BeanUtils.copyProperties(data, result);

		return result;
	}

	/**
	* 分页查询
	* @param param：查询参数
	* @param pageParam ::分页参数
	* @return ：PageInfo<SysUser:分页结果集
	* @author jack
	* @date 2020/01/15 18:12
	*/
	@Override
	public PageInfo<SysUserVo> pageInfo(SysUserArgs param, PageParam pageParam){

		//参数类型转化
		SysUser sysUser = new SysUser();
		BeanUtils.copyProperties(param,sysUser);

		PageHelper.startPage(pageParam.getPageNum(),pageParam.getPageSize());

		//分页查询
		List<SysUser> data = sysUserMapper.list(sysUser);

		//类型转化
		List<SysUserVo> result = JSON.parseArray(JSON.toJSONString(data), SysUserVo.class);

		PageInfo pageInfo=new PageInfo(data);
		pageInfo.setList(result);

		return pageInfo;
	}

	/**
	* 获取查询列表
	* @param param
	* @return ：int 数量
	* @author jack
	* @date 2020/01/15 18:12
	*/
	@Override
	public int insert(SysUser param){

		int result=sysUserMapper.insert(param);
		return result;
	}

	/**
	* 不为空新增
	* @param param
	* @return ：int 数量
	* @author jack
	* @date 2020/01/15 18:12
	*/
	@Override
	public int insertSelective(SysUserArgs param){

		//参数类型转化
		SysUser sysUser = new SysUser();
		BeanUtils.copyProperties(param,sysUser);

		int result=sysUserMapper.insertSelective(sysUser);

		return result;
	}

	/**
	* 批量新增
	* @param param
	* @return ：int 数量
	* @author jack
	* @date 2020/01/15 18:12
	*/
	@Override
	public int batchInsert(List<SysUser> param){

		int result=sysUserMapper.batchInsert(param);
		return result;
	}

	/**
	* 根据主键更新全量
	* @param param
	* @return ：int 数量
	* @author jack
	* @date 2020/01/15 18:12
	*/
	@Override
	public int updateByPrimaryKey(SysUser param){

		int result=sysUserMapper.updateByPrimaryKey(param);
		return result;
	}

	/**
	* 根据主键更新全量

	* @param param
	* @return ：int 数量
	* @author jack
	* @date 2020/01/15 18:12
	*/
	@Override
	public int updateSelectiveByPrimaryKey(SysUserArgs param){

		//参数类型转化
		SysUser sysUser = new SysUser();
		BeanUtils.copyProperties(param,sysUser);

		int result=sysUserMapper.updateSelectiveByPrimaryKey( sysUser);

		return result;
	}

	/**
	* 存在即更新，不存在就插入
	* @param param
	* @author jack
	* @date 2020/01/15 18:12
	*/
	@Override
	public int batchInsertUpdate(List<SysUser> param){

		int result=sysUserMapper.batchInsertUpdate(param);
		return result;
	}

	/**
	* 批量更新入
	* @param param
	* @author jack
	* @date 2020/01/15 18:12
	*/
	@Override
	public int batchUpdate(List<SysUser> param){

		int result=sysUserMapper.batchUpdate(param);
		return result;
	}

	/**
	* 根据主键删除数据
	* @param id
	* @author jack
	* @date 2020/01/15 18:12
	*/
	@Override
	public int deleteByPrimaryKey(Integer id){

		int result=sysUserMapper.deleteByPrimaryKey(id);
		return result;
	}

	/**
	* 根据查询删除数据
	* @param param
	* @author jack
	* @date 2020/01/15 18:12
	*/
	@Override
	public int deleteBySelect(SysUser param){

		int result=sysUserMapper.deleteBySelect(param);
		return result;
	}



}