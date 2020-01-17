package com.wdk.code.general.server.web.controller;

import com.github.pagehelper.PageInfo;
import com.wdk.code.general.server.service.GeneralApiService;
import com.wdk.code.general.server.web.args.GeneralApiArgs;
import com.wdk.code.general.server.web.vo.GeneralApiVo;
import com.wdk.general.core.common.enums.ResponseStatusEnum;
import com.wdk.general.core.common.model.PageParam;
import com.wdk.general.core.common.model.ResponseVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



//API信息维护表页面控制类
@RestController
@RequestMapping("api/general/api")
public class GeneralApiController {

	//定义生成logger对象
	private static Logger logger=LoggerFactory.getLogger(GeneralApiController.class);

	//API信息维护表逻辑处理对象
	@Autowired
	private GeneralApiService generalApiService;

	/**
	* 分页查询接口
	*
	* @param generalApi
	* @author jack
	* @date 2020/01/15 18:12
	*/
	@RequestMapping(value = "",name = "分页查询接口")
	public ResponseVo<PageInfo<GeneralApiVo>> index(GeneralApiArgs generalApi, PageParam pageParam){

		//调用业务逻辑
		PageInfo<GeneralApiVo> result = generalApiService.pageInfo(generalApi, pageParam);

		return new ResponseVo(ResponseStatusEnum.SUCCESS, result);
	}

	/**
	* 查询数据列表接口
	*
	* @param generalApi
	* @author jack
	* @date 2020/01/15 18:12
	*/
	@RequestMapping(value = "list",name = "查询数据列表接口")
	public ResponseVo<List<GeneralApiVo>> list(GeneralApiArgs generalApi){

		//调用业务逻辑
		List<GeneralApiVo> result = generalApiService.list(generalApi);

		return new ResponseVo(ResponseStatusEnum.SUCCESS, result);
	}

	/**
	* 根据条件统计总量接口
	*
	* @param generalApi
	* @author jack
	* @date 2020/01/15 18:12
	*/
	@RequestMapping(value = "count",name = "根据条件统计总量接口")
	public ResponseVo<Integer> count(GeneralApiArgs generalApi){

		//调用业务逻辑
		Integer result = generalApiService.count(generalApi);

		return new ResponseVo(ResponseStatusEnum.SUCCESS, result);
	}

	/**
	* 新增接口
	*
	* @param generalApi
	* @author jack
	* @date 2020/01/15 18:12
	*/
	@PostMapping(value = "insert",name = "新增接口")
	public ResponseVo<Integer> insert(@RequestBody GeneralApiArgs generalApi){

		//调用业务逻辑
		Integer result = generalApiService.insertSelective(generalApi);

		return new ResponseVo(ResponseStatusEnum.SUCCESS, result);
	}

	/**
	* 更新接口
	*
	* @param generalApi
	* @author jack
	* @date 2020/01/15 18:12
	*/
	@PostMapping(value = "update",name = "更新接口")
	public ResponseVo<Integer> update(@RequestBody GeneralApiArgs generalApi){

		//调用业务逻辑
		Integer result = generalApiService.updateSelectiveByPrimaryKey(generalApi);

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
		Integer result = generalApiService.deleteByPrimaryKey(id);

		return new ResponseVo(ResponseStatusEnum.SUCCESS, result);
	}

	/**
	 * 详情
	 *
	 * @param id
	 * @return
	 */
	@GetMapping(value = "detail/{id}", name = "详情方法")
	public ResponseVo<GeneralApiVo> detail(@PathVariable("id") Integer id) {
		//调用业务逻辑
		GeneralApiVo result = generalApiService.selectByPrimaryKey(id);

		return new ResponseVo(ResponseStatusEnum.SUCCESS, result);
	}


}
