package com.sojson.blog.service.impl;

import java.util.List;

import com.sojson.blog.service.BlogService;
import com.sojson.blog.spider.ExtractService;
import com.sojson.blog.spider.LinkTypeData;
import com.sojson.common.model.Blogs;

public class BlogsExtractThread implements Runnable  {

	private BlogService blogService;
	
	public BlogsExtractThread(BlogService blogService){
		this.blogService=blogService;
	}
	
	
	//实现接口
	@Override
	public void run() {		
		ExtractService extractService=new ExtractService();
		List<LinkTypeData> cnblogs=extractService.getCnblogs();
		for (LinkTypeData linkTypeData : cnblogs) {
			Blogs blogs=new Blogs();
			blogs.setBlogcontent(linkTypeData.getContent());
			blogs.setBloghref(linkTypeData.getLinkHref());
			blogs.setBlogsummary(linkTypeData.getSummary());
			blogs.setBlogtext(linkTypeData.getLinkText());
			blogService.insertSelective(blogs);
			System.out.println("保存数据....");
		}	    
	}

}
