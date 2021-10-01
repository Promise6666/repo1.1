package com.itheima.springmvc.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itheima.springmvc.service.ItemServiceImpl;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.itheima.springmvc.pojo.Items;
import com.itheima.springmvc.pojo.QueryVo;
import com.itheima.springmvc.service.ItemService;

/**
 * 商品管理
 * 
 * @author lx
 *
 */
@Controller
public class ItemController {

	@Autowired
	private ItemServiceImpl itemService;


	//入门程序 第一   包类 + 类包 + 方法名



	@RequestMapping(value = "/item/itemlist.action")
	public ModelAndView itemList()
	{
		List<Items> list = itemService.selectItemsList();
		//从Mysql查询
		ModelAndView mav = new ModelAndView();
		mav.addObject("itemList",list);
		mav.setViewName("itemList");  //jsp下的试图名
		return mav;
	}

	//去修改页面入参id
	@RequestMapping(value = "/itemEdit.action")
	//public ModelAndView toEdit(@RequestParam(value = "id",required = false,defaultValue = "1") Integer iddddd,HttpServletRequest request,HttpServletResponse response,HttpSession session,Model model)
	public ModelAndView toEdit(Integer id,HttpServletRequest request,HttpServletResponse response,HttpSession session,Model model)
	{
		//Servlet时代开发
		//String id = request.getParameter("id");

		//查询一个商品
		//Items items = itemService.selectItemsById(Integer.parseInt(id));
		Items items = itemService.selectItemsById(id);
		ModelAndView mav = new ModelAndView();
		mav.addObject("item",items);
		mav.setViewName("editItem");   //editItem.jsp
		return mav;
	}

	//提交修改页面入参 为Items对象 updateitem.action
	@RequestMapping(value = "/updateitem.action")
	//public ModelAndView updateitem(Items items)
	public ModelAndView updateitem(QueryVo vo)
	{
		itemService.updateItemsById(vo.getItems());
		ModelAndView mav = new ModelAndView();
		mav.setViewName("success");
		return mav;
	}
}
