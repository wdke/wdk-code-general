package com.wdk.code.general.server.web.controller;

import com.github.pagehelper.PageInfo;
import com.wdk.code.general.server.service.ProjectMetadataService;
import com.wdk.code.general.server.web.args.ProjectMetadataArgs;
import com.wdk.code.general.server.web.vo.ProjectMetadataVo;
import com.wdk.general.core.common.enums.ResponseStatusEnum;
import com.wdk.general.core.common.model.PageParam;
import com.wdk.general.core.common.model.ResponseVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//项目生成信息信息页面控制类
@RestController
@RequestMapping("api/project/metadata")
public class ProjectMetadataController {

	//定义生成logger对象
	private static Logger logger=LoggerFactory.getLogger(ProjectMetadataController.class);

	//项目生成信息信息逻辑处理对象
	@Autowired
	private ProjectMetadataService projectMetadataService;

	/**
	* 分页查询接口
	*
	* @param projectMetadata
	* @author jack
	* @date 2020/03/12 14:54
	*/
	@RequestMapping(value = "",name = "分页查询接口")
	public ResponseVo<PageInfo<ProjectMetadataVo>> index(ProjectMetadataArgs projectMetadata, PageParam pageParam){

		//调用业务逻辑
		PageInfo<ProjectMetadataVo> result = projectMetadataService.pageInfo(projectMetadata, pageParam);

		return new ResponseVo(ResponseStatusEnum.SUCCESS, result);
	}

	/**
	* 查询数据列表接口
	*
	* @param projectMetadata
	* @author jack
	* @date 2020/03/12 14:54
	*/
	@RequestMapping(value = "list",name = "查询数据列表接口")
	public ResponseVo<List<ProjectMetadataVo>> list(ProjectMetadataArgs projectMetadata){

		//调用业务逻辑
		List<ProjectMetadataVo> result = projectMetadataService.list(projectMetadata);

		return new ResponseVo(ResponseStatusEnum.SUCCESS, result);
	}

	/**
	* 根据条件统计总量接口
	*
	* @param projectMetadata
	* @author jack
	* @date 2020/03/12 14:54
	*/
	@RequestMapping(value = "count",name = "根据条件统计总量接口")
	public ResponseVo<Integer> count(ProjectMetadataArgs projectMetadata){

		//调用业务逻辑
		Integer result = projectMetadataService.count(projectMetadata);

		return new ResponseVo(ResponseStatusEnum.SUCCESS, result);
	}

	/**
	* 新增接口
	*
	* @param projectMetadata
	* @author jack
	* @date 2020/03/12 14:54
	*/
	@PostMapping(value = "insert",name = "新增接口")
	public ResponseVo<Integer> insert(@RequestBody ProjectMetadataArgs projectMetadata){

		//调用业务逻辑
		Integer result = projectMetadataService.insertSelective(projectMetadata);

		return new ResponseVo(ResponseStatusEnum.SUCCESS, result);
	}

	/**
	* 更新接口
	*
	* @param projectMetadata
	* @author jack
	* @date 2020/03/12 14:54
	*/
	@PostMapping(value = "update",name = "更新接口")
	public ResponseVo<Integer> update(@RequestBody ProjectMetadataArgs projectMetadata){

		//调用业务逻辑
		Integer result = projectMetadataService.updateSelectiveByPrimaryKey(projectMetadata);

		return new ResponseVo(ResponseStatusEnum.SUCCESS, result);
	}

	/**
	 * 移除方法
	 *
	 * @param id
	 * @return
	 */
	@PostMapping(value = "remove/{id}", name = "移除方法")
	public ResponseVo<Integer> remove(@PathVariable("id") Integer id) {
		//调用业务逻辑
		Integer result = projectMetadataService.deleteByPrimaryKey(id);

		return new ResponseVo(ResponseStatusEnum.SUCCESS, result);
	}

	/**
	 * 详情
	 *
	 * @param id
	 * @return
	 */
	@GetMapping(value = "detail/{id}", name = "详情方法")
	public ResponseVo<ProjectMetadataVo> detail(@PathVariable("id") Integer id) {
		//调用业务逻辑
		ProjectMetadataVo result = projectMetadataService.selectByPrimaryKey(id);

		return new ResponseVo(ResponseStatusEnum.SUCCESS, result);
	}


}
