package com.henry.ssm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.henry.ssm.po.Items;
import com.henry.ssm.po.ItemsCustom;
import com.henry.ssm.po.ItemsQueryVo;
import com.henry.ssm.service.impl.ItemsService;

/**
 * @author 作者 E-mail:
 * @date 创建时间：2016年5月25日 下午3:05:14
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
@Controller
// 请求添加根路径
@RequestMapping("/items")
public class ItemsController {

	@Autowired
	private ItemsService itemsService;

	/**
	 * 
	 * <p>
	 * description: 包装pojo参数名： itemsCustom.itemName
	 * </P>
	 * 
	 * @return
	 * @param
	 * @date 2016年5月27日下午3:10:12
	 *
	 */
	@RequestMapping("/queryItems")
	public ModelAndView queryItems(ItemsQueryVo itemsQueryVo) throws Exception {

		List<ItemsCustom> itemsList = itemsService.findItemsList(itemsQueryVo);

		// 返回modleandview
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject("itemsList", itemsList);
		// modelAndView.setViewName("/WEB-INF/jsp/items/itemsList.jsp");
		modelAndView.setViewName("items/itemsList");

		return modelAndView;
	}

	/**
	 * 
	 * <p>
	 * description:修改商品
	 * </P>
	 * 
	 * @return 返回视图
	 * @param
	 * @date 2016年5月26日下午12:29:36
	 *
	 *//*
		 * //@RequestMapping("/editItems") //请求限制 post。或get
		 * 
		 * @RequestMapping(value="/editItems",method={RequestMethod.POST,
		 * RequestMethod.GET}) public ModelAndView editItems() throws Exception
		 * {
		 * 
		 * ModelAndView modelAndView = new ModelAndView();
		 * 
		 * ItemsCustom item = itemsService.findItemsById(1);
		 * 
		 * modelAndView.addObject("item", item);
		 * 
		 * modelAndView.setViewName("items/editItems");
		 * 
		 * return modelAndView;
		 * 
		 * }
		 */

	/*	
		*//**
			 * 
			 * <p>
			 * description:修改商品
			 * </P>
			 * 
			 * @return 返回逻辑视图
			 * @param
			 * @date 2016年5月26日下午12:29:36
			 *
			 */
	/*
	 * //@RequestMapping("/editItems") //请求限制 post。或get
	 * 
	 * @RequestMapping(value="/editItems",method={RequestMethod.POST,
	 * RequestMethod.GET})
	 *//**
		 * 
		 * <p>
		 * description: 第一种参数绑定参数名id要和对应客户端的参数名一致
		 * </P>
		 * 
		 * @return
		 * @param
		 * @date 2016年5月27日下午1:14:38
		 *
		 *//*
		 * public String editItems(Model model ,Integer id) throws Exception {
		 * 
		 * 
		 * ItemsCustom item = itemsService.findItemsById(id);
		 * 
		 * model.addAttribute("item", item); return "items/editItems";
		 * 
		 * }
		 */

	/**
	 * 
	 * <p>
	 * description:修改商品
	 * </P>
	 * 
	 * @return 返回逻辑视图
	 * @param
	 * @date 2016年5月26日下午12:29:36
	 *
	 */
	// @RequestMapping("/editItems")
	// 请求限制 post。或get
	@RequestMapping(value = "/editItems", method = { RequestMethod.POST, RequestMethod.GET })
	/**
	 * 
	 * <p>
	 * description:简单类型绑定: 第二种参数绑定使用注解，不必参数名一样
	 * </P>
	 * 
	 * @return
	 * @param
	 * @date 2016年5月27日下午1:14:38
	 *
	 */
	public String editItems(Model model,
			@RequestParam(value = "id", required = true, defaultValue = "") Integer items_id) throws Exception {
		// required表示id是必须传入的参数
		// Default 表示如果没有传入参数，会使用默认值

		ItemsCustom item = itemsService.findItemsById(items_id);

		model.addAttribute("item", item);
		return "items/editItems";

	}

	/**
	 * 
	 * <p>
	 * description: 修改提交商品
	 * </P>
	 * 
	 * @return
	 * @param
	 * @date 2016年5月26日下午12:35:04 在需要校验的pojo前面添加@Validated
	 *       在需要校验的pojo后边添加BindinResult bindingresult接收校验出错信息
	 *       注意@validated和bindingresult 是配对出现
	 *       pojo参数绑定，pojo对应的属性名必须和客户端post提交的name名一致
	 */
	@RequestMapping("/editItemsUpdate")
	public String editItemsUpdate(Model model, Integer id, 
	@Validated ItemsCustom custom, BindingResult bindingResult)
			throws Exception {

		//如果有错误输出错误
		if(bindingResult.hasErrors()){
			
			List<ObjectError> allErrors = bindingResult.getAllErrors();
			for (ObjectError objectError : allErrors) {
				
				System.out.println(objectError.getDefaultMessage());
			}
			//返回错误到页面
			model.addAttribute("allErrors", allErrors);
			
			return "items/editItems";
		}
		
		
		
		
		itemsService.updateItemsById(id, custom);

		// 重定向
		return "redirect:queryItems.action";
		// 转发
		// return "forward:queryItems.action";
	}

	/*
	*//**
		 * 
		 * <p>
		 * description: 修改提交商品
		 * </P>
		 * 
		 * @return
		 * @param
		 * @date 2016年5月26日下午12:35:04
		 *
		 *//*
		 * @RequestMapping("/editItemsUpdate") public void
		 * editItemsUpdate(HttpServletRequest request,HttpServletResponse
		 * response)throws Exception{
		 * 
		 * 
		 * //可以返回json数据 }
		 */

	/*	*//**
			 * 
			 * <p>
			 * description: 修改提交商品
			 * </P>
			 * 
			 * @return
			 * @param
			 * @date 2016年5月26日下午12:35:04
			 *
			 *//*
			 * @RequestMapping("/editItemsUpdate") public ModelAndView
			 * editItemsUpdate()throws Exception{
			 * 
			 * ModelAndView modelAndView = new ModelAndView();
			 * 
			 * 
			 * modelAndView.setViewName("success");
			 * 
			 * return modelAndView; }
			 */

	/**
	 * 
	 * <p>
	 * description:传递数组批量删除
	 * </P>
	 * 
	 * @return
	 * @param
	 * @date 2016年5月27日下午3:33:58
	 *
	 */
	@RequestMapping("/deleteItems")
	public String deleteItems(Integer[] items_ids) throws Exception {

		// 调用service删除
		// 重定向到页面
		return "redirect:queryItems.action";

	}

	/**
	 * 
	 * 批量修修改
	 * <p>
	 * description:
	 * </P>
	 * 
	 * @return
	 * @param
	 * @date 2016年5月27日下午3:56:53
	 *
	 */

	@RequestMapping("/editqueryItems")
	public ModelAndView editqueryItems() throws Exception {

		List<ItemsCustom> itemsList = itemsService.findItemsList(null);

		// 返回modleandview
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject("itemsList", itemsList);
		// modelAndView.setViewName("/WEB-INF/jsp/items/itemsList.jsp");
		modelAndView.setViewName("items/edititemsList");

		return modelAndView;
	}

	/**
	 * 修改提交 包装类型中加入list 实现批量修改数据
	 * <p>
	 * description:
	 * </P>
	 * 
	 * @return
	 * @param
	 * @date 2016年5月27日下午4:06:36
	 *
	 */
	@RequestMapping("/editAllSubmit")
	public String editAllSubmit(ItemsQueryVo itemsQueryVo) throws Exception {

		// service......

		return "redirect:queryItems.action";
	}

}
