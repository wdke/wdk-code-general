package com.wdk.code.general.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wdk.code.general.server.storage.entity.ProjectMetadata;
import com.wdk.code.general.server.web.args.ProjectMetadataArgs;
import com.wdk.code.general.server.web.vo.ProjectMetadataVo;
import com.wdk.code.general.server.service.ProjectMetadataService;
import com.wdk.code.general.server.storage.dao.ProjectMetadataMapper;
import com.alibaba.fastjson.JSON;
import com.wdk.general.core.common.model.PageParam;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Map;

/**
 *项目生成信息信息
 * @author wdke
 * @date 2020/01/15
 */
@Service
public class ProjectMetadataServiceImpl implements ProjectMetadataService{

	private static Logger logger=LoggerFactory.getLogger(ProjectMetadataServiceImpl.class);

	@Autowired
	private ProjectMetadataMapper projectMetadataMapper;



	/**
	* 查询返回key为数据库字段的map
	* @param param ：查询条件Map,key为数据库字段，value为值
	* @return ：List<Map<String,Object>>
	* @author jack
	* @date 2020/01/15 18:12
	*/
	@Override
	public List<Map<String,Object>> selectListByMapReturnMap(Map<String,Object> param){

		List<Map<String,Object>> result=projectMetadataMapper.selectListByMapReturnMap(param);
		return result;
	}

	/**
	* 根据map获取查询列表
	* @param param ：查询条件Map,key为java对应字段，value为值
	* @return ：List<ProjectMetadata
	* @author jack
	* @date 2020/01/15 18:12
	*/
	@Override
	public List<ProjectMetadata> selectListByMap(Map<String,Object> param){

		List<ProjectMetadata> result=projectMetadataMapper.selectListByMap(param);
		return result;
	}

	/**
	* 获取查询列表
	* @param param
	* @return ：List<ProjectMetadataVo>
	* @author jack
	* @date 2020/01/15 18:12
	*/
	@Override
	public List<ProjectMetadataVo> list(ProjectMetadataArgs param){

		//参数类型转化
		ProjectMetadata projectMetadata = new ProjectMetadata();

		BeanUtils.copyProperties(param,projectMetadata);

		//访问数据库
		List<ProjectMetadata> data = projectMetadataMapper.list(projectMetadata);

		//类型转化
		List<ProjectMetadataVo> result = JSON.parseArray(JSON.toJSONString(data), ProjectMetadataVo.class);

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
	public Integer count(ProjectMetadataArgs param){

		//参数类型转化
		ProjectMetadata projectMetadata = new ProjectMetadata();

		BeanUtils.copyProperties(param,projectMetadata);

		Integer result=projectMetadataMapper.count(projectMetadata);

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
	public ProjectMetadataVo selectByPrimaryKey(Integer id){

		//查询数据库
		ProjectMetadata data = projectMetadataMapper.selectByPrimaryKey(id);

		//判断是否查询到数据
		if (null == data) {
			return null;
		}		//类型转化
		ProjectMetadataVo result = new ProjectMetadataVo();
		BeanUtils.copyProperties(data, result);

		return result;
	}

	/**
	* 分页查询
	* @param param：查询参数
	* @param pageParam ::分页参数
	* @return ：PageInfo<ProjectMetadata:分页结果集
	* @author jack
	* @date 2020/01/15 18:12
	*/
	@Override
	public PageInfo<ProjectMetadataVo> pageInfo(ProjectMetadataArgs param, PageParam pageParam){

		//参数类型转化
		ProjectMetadata projectMetadata = new ProjectMetadata();
		BeanUtils.copyProperties(param,projectMetadata);

		PageHelper.startPage(pageParam.getPageNum(),pageParam.getPageSize());

		//分页查询
		List<ProjectMetadata> data = projectMetadataMapper.list(projectMetadata);

		//类型转化
		List<ProjectMetadataVo> result = JSON.parseArray(JSON.toJSONString(data), ProjectMetadataVo.class);

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
	public int insert(ProjectMetadata param){

		int result=projectMetadataMapper.insert(param);
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
	public int insertSelective(ProjectMetadataArgs param){

		//参数类型转化
		ProjectMetadata projectMetadata = new ProjectMetadata();
		BeanUtils.copyProperties(param,projectMetadata);

		int result=projectMetadataMapper.insertSelective(projectMetadata);

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
	public int batchInsert(List<ProjectMetadata> param){

		int result=projectMetadataMapper.batchInsert(param);
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
	public int updateByPrimaryKey(ProjectMetadata param){

		int result=projectMetadataMapper.updateByPrimaryKey(param);
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
	public int updateSelectiveByPrimaryKey(ProjectMetadataArgs param){

		//参数类型转化
		ProjectMetadata projectMetadata = new ProjectMetadata();
		BeanUtils.copyProperties(param,projectMetadata);

		int result=projectMetadataMapper.updateSelectiveByPrimaryKey( projectMetadata);

		return result;
	}

	/**
	* 存在即更新，不存在就插入
	* @param param
	* @author jack
	* @date 2020/01/15 18:12
	*/
	@Override
	public int batchInsertUpdate(List<ProjectMetadata> param){

		int result=projectMetadataMapper.batchInsertUpdate(param);
		return result;
	}

	/**
	* 批量更新入
	* @param param
	* @author jack
	* @date 2020/01/15 18:12
	*/
	@Override
	public int batchUpdate(List<ProjectMetadata> param){

		int result=projectMetadataMapper.batchUpdate(param);
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

		int result=projectMetadataMapper.deleteByPrimaryKey(id);
		return result;
	}

	/**
	* 根据查询删除数据
	* @param param
	* @author jack
	* @date 2020/01/15 18:12
	*/
	@Override
	public int deleteBySelect(ProjectMetadata param){

		int result=projectMetadataMapper.deleteBySelect(param);
		return result;
	}



}