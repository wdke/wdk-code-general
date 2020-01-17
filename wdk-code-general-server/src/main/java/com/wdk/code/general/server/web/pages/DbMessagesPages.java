package com.wdk.code.general.server.web.pages;

import com.wdk.code.general.server.service.DbMessagesService;
import com.github.pagehelper.PageInfo;
import com.wdk.code.general.server.web.args.DbMessagesArgs;
import com.wdk.code.general.server.web.vo.DbMessagesVo;
import com.wdk.general.core.common.enums.ResponseStatusEnum;
import com.wdk.general.core.common.model.PageParam;
import com.wdk.general.core.common.model.ResponseVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



//数据源信息页面控制类
@Controller
@RequestMapping("pages/db/messages")
public class DbMessagesPages {

	//定义生成logger对象
	private static Logger logger=LoggerFactory.getLogger(DbMessagesPages.class);

	//数据源信息逻辑处理对象
	@Autowired
	private DbMessagesService dbMessagesService;

	/**
	* 首页接口
	*
	* @param dbMessagesArgs
	* @date 2020/01/15 18:12
	*/
	@RequestMapping(value = "",name = "首页接口")
	public String index(DbMessagesArgs dbMessagesArgs, PageParam pageParam, Model model){

		//调用业务逻辑
		PageInfo<DbMessagesVo> result = dbMessagesService.pageInfo(dbMessagesArgs, pageParam);

		//数据返回jsp
		model.addAttribute("datas", result);

		return "/dbMessages/index";
	}

	/**
	 * 新增页面路由
	 *
	 * @return
	 */
	@GetMapping(value = "insert/pages", name = "新增页面路由")
	public String insertPage() {

		return "/dbMessages/insert";
	}

	/**
	* 新增接口
	*
	* @param dbMessagesArgs
	* @date 2020/01/15 18:12
	*/
	@PostMapping(value = "insert",name = "新增接口")
	public String insert(DbMessagesArgs dbMessagesArgs){

		//调用业务逻辑
		Integer result = dbMessagesService.insertSelective(dbMessagesArgs);

		return "redirect:/db/messages";
	}

	/**
	* 进入更新页面
	*
	* @param id
	* @date 2020/01/15 18:12
	*/
	@GetMapping(value = "update/pages//{id}",name = "进入更新页面")
	public String updatePage(@PathVariable("id") Integer id, Model model){

		//根据主键查询数据
		DbMessagesVo result = dbMessagesService.selectByPrimaryKey(id);

		//数据返回html
		model.addAttribute("data", result);

		//返回页面路径
		return "/dbMessages/update";
	}

	/**
	* 进入详情页面
	*
	* @param id
	* @date 2020/01/15 18:12
	*/
	@GetMapping(value = "detail/pages//{id}",name = "进入更新页面")
	public String detailPage(@PathVariable("id") Integer id, Model model){

		//根据主键查询数据
		DbMessagesVo result = dbMessagesService.selectByPrimaryKey(id);

		//数据返回html
		model.addAttribute("data", result);

		//返回页面路径
		return "/dbMessages/detail";
	}

	/**
	* 更新接口
	*
	* @param dbMessagesArgs
	* @date 2020/01/15 18:12
	*/
	@PostMapping(value = "update",name = "更新接口")
	public String update(DbMessagesArgs dbMessagesArgs){

		//调用业务逻辑
		Integer result = dbMessagesService.updateSelectiveByPrimaryKey(dbMessagesArgs);

		return "redirect:/db/messages";
	}

	/**
	 * 移除方法
	 *
	 * @param id
	 * @return
	 */
	@PostMapping(value = "remove//{id}", name = "移除方法")
	@ResponseBody
	public ResponseVo<Integer> remove(@PathVariable("id") Integer id) {
		//调用业务逻辑
		Integer result = dbMessagesService.deleteByPrimaryKey(id);



		return new ResponseVo<>(ResponseStatusEnum.SUCCESS, result);

	}
}
