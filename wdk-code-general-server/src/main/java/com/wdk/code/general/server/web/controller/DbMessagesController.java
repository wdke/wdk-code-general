package com.wdk.code.general.server.web.controller;

import com.github.pagehelper.PageInfo;
import com.wdk.code.general.server.service.DbMessagesService;
import com.wdk.code.general.server.web.args.DbMessagesArgs;
import com.wdk.code.general.server.web.vo.DbMessagesVo;
import com.wdk.general.core.common.enums.ResponseStatusEnum;
import com.wdk.general.core.common.model.PageParam;
import com.wdk.general.core.common.model.ResponseVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



//数据源信息页面控制类
@RestController
@RequestMapping("api/db/messages")
public class DbMessagesController {

	//定义生成logger对象
	private static Logger logger=LoggerFactory.getLogger(DbMessagesController.class);

	//数据源信息逻辑处理对象
	@Autowired
	private DbMessagesService dbMessagesService;

	/**
	* 分页查询接口
	*
	* @param dbMessages
	* @author jack
	* @date 2020/01/15 18:12
	*/
	@RequestMapping(value = "",name = "分页查询接口")
	public ResponseVo<PageInfo<DbMessagesVo>> index(DbMessagesArgs dbMessages, PageParam pageParam){

		//调用业务逻辑
		PageInfo<DbMessagesVo> result = dbMessagesService.pageInfo(dbMessages, pageParam);

		return new ResponseVo(ResponseStatusEnum.SUCCESS, result);
	}

	/**
	* 查询数据列表接口
	*
	* @param dbMessages
	* @author jack
	* @date 2020/01/15 18:12
	*/
	@RequestMapping(value = "list",name = "查询数据列表接口")
	public ResponseVo<List<DbMessagesVo>> list(DbMessagesArgs dbMessages){

		//调用业务逻辑
		List<DbMessagesVo> result = dbMessagesService.list(dbMessages);

		return new ResponseVo(ResponseStatusEnum.SUCCESS, result);
	}

	/**
	* 根据条件统计总量接口
	*
	* @param dbMessages
	* @author jack
	* @date 2020/01/15 18:12
	*/
	@RequestMapping(value = "count",name = "根据条件统计总量接口")
	public ResponseVo<Integer> count(DbMessagesArgs dbMessages){

		//调用业务逻辑
		Integer result = dbMessagesService.count(dbMessages);

		return new ResponseVo(ResponseStatusEnum.SUCCESS, result);
	}

	/**
	* 新增接口
	*
	* @param dbMessages
	* @author jack
	* @date 2020/01/15 18:12
	*/
	@PostMapping(value = "insert",name = "新增接口")
	public ResponseVo<Integer> insert(@RequestBody DbMessagesArgs dbMessages){

		//调用业务逻辑
		Integer result = dbMessagesService.insertSelective(dbMessages);

		return new ResponseVo(ResponseStatusEnum.SUCCESS, result);
	}

	/**
	* 更新接口
	*
	* @param dbMessages
	* @author jack
	* @date 2020/01/15 18:12
	*/
	@PostMapping(value = "update",name = "更新接口")
	public ResponseVo<Integer> update(@RequestBody DbMessagesArgs dbMessages){

		//调用业务逻辑
		Integer result = dbMessagesService.updateSelectiveByPrimaryKey(dbMessages);

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
		Integer result = dbMessagesService.deleteByPrimaryKey(id);

		return new ResponseVo(ResponseStatusEnum.SUCCESS, result);
	}

	/**
	 * 详情
	 *
	 * @param id
	 * @return
	 */
	@GetMapping(value = "detail/{id}", name = "详情方法")
	public ResponseVo<DbMessagesVo> detail(@PathVariable("id") Integer id) {
		//调用业务逻辑
		DbMessagesVo result = dbMessagesService.selectByPrimaryKey(id);

		return new ResponseVo(ResponseStatusEnum.SUCCESS, result);
	}


}
