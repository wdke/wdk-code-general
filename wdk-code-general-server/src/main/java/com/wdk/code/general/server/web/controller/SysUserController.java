package com.wdk.code.general.server.web.controller;

import com.wdk.code.general.server.service.SysUserService;
import com.github.pagehelper.PageInfo;
import com.wdk.code.general.server.web.args.SysUserArgs;
import com.wdk.code.general.server.web.vo.SysUserVo;
import com.wdk.general.core.common.enums.ResponseStatusEnum;
import com.wdk.general.core.common.model.PageParam;
import com.wdk.general.core.common.model.ResponseVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;



//用户管理页面控制类
@RestController
@RequestMapping("api/sys/user")
public class SysUserController {

	//定义生成logger对象
	private static Logger logger=LoggerFactory.getLogger(SysUserController.class);

	//用户管理逻辑处理对象
	@Autowired
	private SysUserService sysUserService;

	/**
	* 分页查询接口
	*
	* @param sysUser
	* @author jack
	* @date 2020/01/15 18:12
	*/
	@RequestMapping(value = "",name = "分页查询接口")
	public ResponseVo<PageInfo<SysUserVo>> index(SysUserArgs sysUser, PageParam pageParam){

		//调用业务逻辑
		PageInfo<SysUserVo> result = sysUserService.pageInfo(sysUser, pageParam);

		return new ResponseVo(ResponseStatusEnum.SUCCESS, result);
	}

	/**
	* 查询数据列表接口
	*
	* @param sysUser
	* @author jack
	* @date 2020/01/15 18:12
	*/
	@RequestMapping(value = "list",name = "查询数据列表接口")
	public ResponseVo<List<SysUserVo>> list(SysUserArgs sysUser){

		//调用业务逻辑
		List<SysUserVo> result = sysUserService.list(sysUser);

		return new ResponseVo(ResponseStatusEnum.SUCCESS, result);
	}

	/**
	* 根据条件统计总量接口
	*
	* @param sysUser
	* @author jack
	* @date 2020/01/15 18:12
	*/
	@RequestMapping(value = "count",name = "根据条件统计总量接口")
	public ResponseVo<Integer> count(SysUserArgs sysUser){

		//调用业务逻辑
		Integer result = sysUserService.count(sysUser);

		return new ResponseVo(ResponseStatusEnum.SUCCESS, result);
	}

	/**
	* 新增接口
	*
	* @param sysUser
	* @author jack
	* @date 2020/01/15 18:12
	*/
	@PostMapping(value = "insert",name = "新增接口")
	public ResponseVo<Integer> insert(@RequestBody SysUserArgs sysUser){

		//调用业务逻辑
		Integer result = sysUserService.insertSelective(sysUser);

		return new ResponseVo(ResponseStatusEnum.SUCCESS, result);
	}

	/**
	* 更新接口
	*
	* @param sysUser
	* @author jack
	* @date 2020/01/15 18:12
	*/
	@PostMapping(value = "update",name = "更新接口")
	public ResponseVo<Integer> update(@RequestBody SysUserArgs sysUser){

		//调用业务逻辑
		Integer result = sysUserService.updateSelectiveByPrimaryKey(sysUser);

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
		Integer result = sysUserService.deleteByPrimaryKey(id);

		return new ResponseVo(ResponseStatusEnum.SUCCESS, result);
	}

	/**
	 * 详情
	 *
	 * @param id
	 * @return
	 */
	@GetMapping(value = "detail/{id}", name = "详情方法")
	public ResponseVo<SysUserVo> detail(@PathVariable("id") Integer id) {
		//调用业务逻辑
		SysUserVo result = sysUserService.selectByPrimaryKey(id);

		return new ResponseVo(ResponseStatusEnum.SUCCESS, result);
	}


}
