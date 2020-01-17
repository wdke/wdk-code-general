package com.wdk.code.general.server.web.pages;

import com.wdk.code.general.server.service.SysUserService;
import com.github.pagehelper.PageInfo;
import com.wdk.code.general.server.web.args.SysUserArgs;
import com.wdk.code.general.server.web.vo.SysUserVo;
import com.wdk.general.core.common.enums.ResponseStatusEnum;
import com.wdk.general.core.common.model.PageParam;
import com.wdk.general.core.common.model.ResponseVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



//用户管理页面控制类
@Controller
@RequestMapping("pages/sys/user")
public class SysUserPages {

	//定义生成logger对象
	private static Logger logger=LoggerFactory.getLogger(SysUserPages.class);

	//用户管理逻辑处理对象
	@Autowired
	private SysUserService sysUserService;

	/**
	* 首页接口
	*
	* @param sysUserArgs
	* @date 2020/01/15 18:12
	*/
	@RequestMapping(value = "",name = "首页接口")
	public String index(SysUserArgs sysUserArgs, PageParam pageParam, Model model){

		//调用业务逻辑
		PageInfo<SysUserVo> result = sysUserService.pageInfo(sysUserArgs, pageParam);

		//数据返回jsp
		model.addAttribute("datas", result);

		return "/sysUser/index";
	}

	/**
	 * 新增页面路由
	 *
	 * @return
	 */
	@GetMapping(value = "insert/pages", name = "新增页面路由")
	public String insertPage() {

		return "/sysUser/insert";
	}

	/**
	* 新增接口
	*
	* @param sysUserArgs
	* @date 2020/01/15 18:12
	*/
	@PostMapping(value = "insert",name = "新增接口")
	public String insert(SysUserArgs sysUserArgs){

		//调用业务逻辑
		Integer result = sysUserService.insertSelective(sysUserArgs);

		return "redirect:/sys/user";
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
		SysUserVo result = sysUserService.selectByPrimaryKey(id);

		//数据返回html
		model.addAttribute("data", result);

		//返回页面路径
		return "/sysUser/update";
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
		SysUserVo result = sysUserService.selectByPrimaryKey(id);

		//数据返回html
		model.addAttribute("data", result);

		//返回页面路径
		return "/sysUser/detail";
	}

	/**
	* 更新接口
	*
	* @param sysUserArgs
	* @date 2020/01/15 18:12
	*/
	@PostMapping(value = "update",name = "更新接口")
	public String update(SysUserArgs sysUserArgs){

		//调用业务逻辑
		Integer result = sysUserService.updateSelectiveByPrimaryKey(sysUserArgs);

		return "redirect:/sys/user";
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
		Integer result = sysUserService.deleteByPrimaryKey(id);



		return new ResponseVo<>(ResponseStatusEnum.SUCCESS, result);

	}
}
