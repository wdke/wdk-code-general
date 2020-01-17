package com.wdk.code.general.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wdk.code.general.server.storage.entity.GeneralApi;
import com.wdk.code.general.server.web.args.GeneralApiArgs;
import com.wdk.code.general.server.web.vo.GeneralApiVo;
import com.wdk.code.general.server.service.GeneralApiService;
import com.wdk.code.general.server.storage.dao.GeneralApiMapper;
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
 *API信息维护表
 * @author wdke
 * @date 2020/01/15
 */
@Service
public class GeneralApiServiceImpl implements GeneralApiService{

	private static Logger logger=LoggerFactory.getLogger(GeneralApiServiceImpl.class);

	@Autowired
	private GeneralApiMapper generalApiMapper;



	/**
	* 查询返回key为数据库字段的map
	* @param param ：查询条件Map,key为数据库字段，value为值
	* @return ：List<Map<String,Object>>
	* @author jack
	* @date 2020/01/15 18:12
	*/
	@Override
	public List<Map<String,Object>> selectListByMapReturnMap(Map<String,Object> param){

		List<Map<String,Object>> result=generalApiMapper.selectListByMapReturnMap(param);
		return result;
	}

	/**
	* 根据map获取查询列表
	* @param param ：查询条件Map,key为java对应字段，value为值
	* @return ：List<GeneralApi
	* @author jack
	* @date 2020/01/15 18:12
	*/
	@Override
	public List<GeneralApi> selectListByMap(Map<String,Object> param){

		List<GeneralApi> result=generalApiMapper.selectListByMap(param);
		return result;
	}

	/**
	* 获取查询列表
	* @param param
	* @return ：List<GeneralApiVo>
	* @author jack
	* @date 2020/01/15 18:12
	*/
	@Override
	public List<GeneralApiVo> list(GeneralApiArgs param){

		//参数类型转化
		GeneralApi generalApi = new GeneralApi();

		BeanUtils.copyProperties(param,generalApi);

		//访问数据库
		List<GeneralApi> data = generalApiMapper.list(generalApi);

		//类型转化
		List<GeneralApiVo> result = JSON.parseArray(JSON.toJSONString(data), GeneralApiVo.class);

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
	public Integer count(GeneralApiArgs param){

		//参数类型转化
		GeneralApi generalApi = new GeneralApi();

		BeanUtils.copyProperties(param,generalApi);

		Integer result=generalApiMapper.count(generalApi);

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
	public GeneralApiVo selectByPrimaryKey(Integer id){

		//查询数据库
		GeneralApi data = generalApiMapper.selectByPrimaryKey(id);

		//判断是否查询到数据
		if (null == data) {
			return null;
		}		//类型转化
		GeneralApiVo result = new GeneralApiVo();
		BeanUtils.copyProperties(data, result);

		return result;
	}

	/**
	* 分页查询
	* @param param：查询参数
	* @param pageParam ::分页参数
	* @return ：PageInfo<GeneralApi:分页结果集
	* @author jack
	* @date 2020/01/15 18:12
	*/
	@Override
	public PageInfo<GeneralApiVo> pageInfo(GeneralApiArgs param, PageParam pageParam){

		//参数类型转化
		GeneralApi generalApi = new GeneralApi();
		BeanUtils.copyProperties(param,generalApi);

		PageHelper.startPage(pageParam.getPageNum(),pageParam.getPageSize());

		//分页查询
		List<GeneralApi> data = generalApiMapper.list(generalApi);

		//类型转化
		List<GeneralApiVo> result = JSON.parseArray(JSON.toJSONString(data), GeneralApiVo.class);

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
	public int insert(GeneralApi param){

		int result=generalApiMapper.insert(param);
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
	public int insertSelective(GeneralApiArgs param){

		//参数类型转化
		GeneralApi generalApi = new GeneralApi();
		BeanUtils.copyProperties(param,generalApi);

		int result=generalApiMapper.insertSelective(generalApi);

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
	public int batchInsert(List<GeneralApi> param){

		int result=generalApiMapper.batchInsert(param);
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
	public int updateByPrimaryKey(GeneralApi param){

		int result=generalApiMapper.updateByPrimaryKey(param);
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
	public int updateSelectiveByPrimaryKey(GeneralApiArgs param){

		//参数类型转化
		GeneralApi generalApi = new GeneralApi();
		BeanUtils.copyProperties(param,generalApi);

		int result=generalApiMapper.updateSelectiveByPrimaryKey( generalApi);

		return result;
	}

	/**
	* 存在即更新，不存在就插入
	* @param param
	* @author jack
	* @date 2020/01/15 18:12
	*/
	@Override
	public int batchInsertUpdate(List<GeneralApi> param){

		int result=generalApiMapper.batchInsertUpdate(param);
		return result;
	}

	/**
	* 批量更新入
	* @param param
	* @author jack
	* @date 2020/01/15 18:12
	*/
	@Override
	public int batchUpdate(List<GeneralApi> param){

		int result=generalApiMapper.batchUpdate(param);
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

		int result=generalApiMapper.deleteByPrimaryKey(id);
		return result;
	}

	/**
	* 根据查询删除数据
	* @param param
	* @author jack
	* @date 2020/01/15 18:12
	*/
	@Override
	public int deleteBySelect(GeneralApi param){

		int result=generalApiMapper.deleteBySelect(param);
		return result;
	}



}