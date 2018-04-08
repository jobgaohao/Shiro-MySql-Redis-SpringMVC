package com.sojson.blog.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.executor.loader.ResultLoaderMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sojson.blog.bo.BlogsBo;
import com.sojson.blog.mapper.BlogMapper;
import com.sojson.blog.service.BlogService;
import com.sojson.common.model.Blogs;
import com.sojson.common.utils.LoggerUtils;
import com.sojson.common.utils.StringUtils;
import com.sojson.core.mybatis.BaseMybatisDao;
import com.sojson.core.mybatis.page.Pagination;


@Service
public class BlogServiceImpl extends BaseMybatisDao<BlogMapper>  implements BlogService {

	@Autowired
	BlogMapper blogMapper;
	
	@Override
	public Pagination<Blogs> findPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize) {
		@SuppressWarnings("unchecked")
		Pagination<Blogs> blogs=super.findPage(resultMap, pageNo, pageSize);
		return blogs;
	}
	/**
	 * 删除博客
	 */
	@Override
	public Map<String, Object> deleteBlogs(String ids) {
		Map<String,Object> resultMap=new HashMap<String,Object>();
		try {
			int successCount=0;
			String resultMsg="删除%s条，失败%s条";
			String[] idArray=new String[]{};
			if(StringUtils.contains(ids, ",")){
				idArray=ids.split(",");
			}
			else{
				idArray=new String[]{ids};
			}
			
			for (String id : idArray) {
				successCount+=blogMapper.deleteByPrimaryKey(Long.parseLong(id));
			}
			
			//成功
			if(successCount==idArray.length){
				resultMap.put("status", 200);
				resultMap.put("resultMsg", "操作成功");
			}else{
				resultMap.put("status",200);
				resultMsg=String.format(resultMsg, successCount,(idArray.length-successCount));
				resultMap.put("resultMsg",resultMsg);
			}			
		} catch (Exception e) {
			resultMap.put("status", 500);
			resultMap.put("resultMsg", "操作失败。");
			LoggerUtils.fmtError(getClass(), e, "根据IDS删除blogs出现错误");
		}
		return resultMap;
	}

	/**
	 * 添加博客
	 */
	@Override
	public Blogs insertSelective(Blogs record) {
		blogMapper.insertSelective(record);
		return record;
	}

	/**
	 * 根据PKID查询博客
	 */
	@Override
	public Blogs getOneByPkid(Long pkid) {
		BlogsBo blogsBo=new BlogsBo();
		blogsBo.setPkid(pkid);
		Blogs blogs=blogMapper.getOneBlogsByPkid(blogsBo);
		return blogs;
	}

	/**
	 * 修改博客
	 */
	@Override
	public Map<String, Object> modifyBlogs(Blogs blogs) {
		Map<String,Object> result=new HashMap<String,Object>(); 
		try {
			blogMapper.modifyBlogs(blogs);
			result.put("status", "200");
		} catch (Exception e) {
			result.put("status", 500);
			result.put("message", e.getMessage());
		}		
		return result;
	}

}
