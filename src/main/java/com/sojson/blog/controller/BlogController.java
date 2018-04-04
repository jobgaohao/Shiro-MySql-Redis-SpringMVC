package com.sojson.blog.controller;

import java.util.HashMap;
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
import com.sojson.blog.service.impl.BlogsExtractThread;
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
		modelMap.put("blogtext",blogsBo.getBlogtext());
		modelMap.put("blogcontent",blogsBo.getBlogcontent());		
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
			new Thread(new BlogsExtractThread(blogService)).start();			
			System.out.println("抓取成功");
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
