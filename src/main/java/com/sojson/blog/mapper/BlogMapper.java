package com.sojson.blog.mapper;

import com.sojson.blog.bo.BlogsBo;
import com.sojson.common.model.Blogs;

public interface BlogMapper {

	/**
     * 删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);
    
    /**
     * 新增
     * @param record
     * @return
     */
    int insertSelective(Blogs fitment);
    
    /**
     * 修改博客
     * @param 
     * @return
     */
    int modifyBlogs(Blogs fitment);
    
    /**
     * 根据PKID获取单个博客
     * @param id
     * @return
     */
    Blogs getOneBlogByPkid(BlogsBo blogsBo);
}
