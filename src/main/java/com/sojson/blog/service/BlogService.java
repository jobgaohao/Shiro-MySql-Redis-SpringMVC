package com.sojson.blog.service;

import java.util.Map;

import com.sojson.common.model.Blogs;
import com.sojson.core.mybatis.page.Pagination;

public interface BlogService {

	/**
	 * 分页查询
	 * @param resultMap
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	Pagination<Blogs> findPage(Map<String, Object> resultMap, Integer pageNo,
			Integer pageSize);
	
	
	/**
	 * 删除博客
	 * @param ids
	 * @return
	 */
	Map<String, Object> deleteBlogs(String ids);
	
	/**
	 * 添加博客
	 * @param record
	 * @return
	 */
	Blogs insertSelective(Blogs record);
	
	/**
	 * 根据PKID获取单个博客
	 * @param pkid
	 * @return
	 */
	Blogs getOneByPkid(Long pkid);
	
	/**
	 * 修改博客
	 * @param blog
	 * @return
	 */
	Map<String,Object> modifyBlogs(Blogs blog);
}
