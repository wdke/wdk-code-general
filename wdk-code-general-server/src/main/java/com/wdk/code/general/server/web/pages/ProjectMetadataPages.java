package com.wdk.code.general.server.web.pages;

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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



//项目生成信息信息页面控制类
@Controller
@RequestMapping("pages/project/metadata")
public class ProjectMetadataPages {

	//定义生成logger对象
	private static Logger logger=LoggerFactory.getLogger(ProjectMetadataPages.class);

	//项目生成信息信息逻辑处理对象
	@Autowired
	private ProjectMetadataService projectMetadataService;

	/**
	* 首页接口
	*
	* @param projectMetadataArgs
	* @date 2020/01/15 18:12
	*/
	@RequestMapping(value = "",name = "首页接口")
	public String index(ProjectMetadataArgs projectMetadataArgs, PageParam pageParam, Model model){

		//调用业务逻辑
		PageInfo<ProjectMetadataVo> result = projectMetadataService.pageInfo(projectMetadataArgs, pageParam);

		//数据返回jsp
		model.addAttribute("datas", result);

		return "/projectMetadata/index";
	}

	/**
	 * 新增页面路由
	 *
	 * @return
	 */
	@GetMapping(value = "insert/pages", name = "新增页面路由")
	public String insertPage() {

		return "/projectMetadata/insert";
	}

	/**
	* 新增接口
	*
	* @param projectMetadataArgs
	* @date 2020/01/15 18:12
	*/
	@PostMapping(value = "insert",name = "新增接口")
	public String insert(ProjectMetadataArgs projectMetadataArgs){

		//调用业务逻辑
		Integer result = projectMetadataService.insertSelective(projectMetadataArgs);

		return "redirect:/project/metadata";
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
		ProjectMetadataVo result = projectMetadataService.selectByPrimaryKey(id);

		//数据返回html
		model.addAttribute("data", result);

		//返回页面路径
		return "/projectMetadata/update";
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
		ProjectMetadataVo result = projectMetadataService.selectByPrimaryKey(id);

		//数据返回html
		model.addAttribute("data", result);

		//返回页面路径
		return "/projectMetadata/detail";
	}

	/**
	* 更新接口
	*
	* @param projectMetadataArgs
	* @date 2020/01/15 18:12
	*/
	@PostMapping(value = "update",name = "更新接口")
	public String update(ProjectMetadataArgs projectMetadataArgs){

		//调用业务逻辑
		Integer result = projectMetadataService.updateSelectiveByPrimaryKey(projectMetadataArgs);

		return "redirect:/project/metadata";
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
		Integer result = projectMetadataService.deleteByPrimaryKey(id);



		return new ResponseVo<>(ResponseStatusEnum.SUCCESS, result);

	}
}
