package com.sojson.blog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sojson.blog.bo.BlogsBo;
import com.sojson.blog.service.BlogService;
import com.sojson.blog.spider.ExtractService;
import com.sojson.blog.spider.LinkTypeData;
import com.sojson.common.aopLog.SystemLog;
import com.sojson.common.controller.BaseController;
import com.sojson.common.model.Blogs;
import com.sojson.core.mybatis.page.Pagination;

@Controller
@Scope(value="prototype")
@RequestMapping("/blogs")
public class BlogController extends BaseController {
    
	@Autowired
	BlogService blogService;
	
	@RequestMapping("/index")
	@SystemLog("查询博客列表")
	public ModelAndView index(BlogsBo blogsBo,ModelMap modelMap,Integer pageNo){
		modelMap.put("bo",blogsBo);
		Pagination<Blogs> blogs=blogService.findPage(modelMap, pageNo, pageSize);
		return new ModelAndView("/blogs/list","page",blogs);		
	}
	
	/**
	 * 抓取博客
	 * @return
	 */
	@RequestMapping("/addBlogs")
	@ResponseBody
	public Map<String,Object> addBlogs(){		
		Map<String,Object> result=new HashMap<String,Object>();
		try {
			ExtractService extractService=new ExtractService();
			List<LinkTypeData> cnblogs=extractService.getCnblogs();
			for (LinkTypeData linkTypeData : cnblogs) {
				Blogs blogs=new Blogs();
				blogs.setBlogcontent(linkTypeData.getContent());
				blogs.setBloghref(linkTypeData.getLinkHref());
				blogs.setBlogsummary(linkTypeData.getSummary());
				blogs.setBlogtext(linkTypeData.getLinkText());
				blogService.insertSelective(blogs);
			}
		    result.put("status", 200);
		    result.put("message", "抓取成功");
		} catch (Exception e) {
			result.put("status", 500);
		    result.put("message", "抓取失败-->"+e.getMessage());
		    e.printStackTrace();
		}
		return result;
	}
}
