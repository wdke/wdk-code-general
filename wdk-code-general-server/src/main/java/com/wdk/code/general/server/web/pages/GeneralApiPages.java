package com.wdk.code.general.server.web.pages;

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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



//API信息维护表页面控制类
@Controller
@RequestMapping("pages/general/api")
public class GeneralApiPages {

	//定义生成logger对象
	private static Logger logger=LoggerFactory.getLogger(GeneralApiPages.class);

	//API信息维护表逻辑处理对象
	@Autowired
	private GeneralApiService generalApiService;

	/**
	* 首页接口
	*
	* @param generalApiArgs
	* @date 2020/01/15 18:12
	*/
	@RequestMapping(value = "",name = "首页接口")
	public String index(GeneralApiArgs generalApiArgs, PageParam pageParam, Model model){

		//调用业务逻辑
		PageInfo<GeneralApiVo> result = generalApiService.pageInfo(generalApiArgs, pageParam);

		//数据返回jsp
		model.addAttribute("datas", result);

		return "/generalApi/index";
	}

	/**
	 * 新增页面路由
	 *
	 * @return
	 */
	@GetMapping(value = "insert/pages", name = "新增页面路由")
	public String insertPage() {

		return "/generalApi/insert";
	}

	/**
	* 新增接口
	*
	* @param generalApiArgs
	* @date 2020/01/15 18:12
	*/
	@PostMapping(value = "insert",name = "新增接口")
	public String insert(GeneralApiArgs generalApiArgs){

		//调用业务逻辑
		Integer result = generalApiService.insertSelective(generalApiArgs);

		return "redirect:/general/api";
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
		GeneralApiVo result = generalApiService.selectByPrimaryKey(id);

		//数据返回html
		model.addAttribute("data", result);

		//返回页面路径
		return "/generalApi/update";
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
		GeneralApiVo result = generalApiService.selectByPrimaryKey(id);

		//数据返回html
		model.addAttribute("data", result);

		//返回页面路径
		return "/generalApi/detail";
	}

	/**
	* 更新接口
	*
	* @param generalApiArgs
	* @date 2020/01/15 18:12
	*/
	@PostMapping(value = "update",name = "更新接口")
	public String update(GeneralApiArgs generalApiArgs){

		//调用业务逻辑
		Integer result = generalApiService.updateSelectiveByPrimaryKey(generalApiArgs);

		return "redirect:/general/api";
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
		Integer result = generalApiService.deleteByPrimaryKey(id);



		return new ResponseVo<>(ResponseStatusEnum.SUCCESS, result);

	}
}
