package com.sojson.common.model;

import java.io.Serializable;
import java.util.Date;

public class Blogs implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long pkid;
	private String blogtext;//博客标题
	private String bloghref;//博客链接
	private String blogcontent;//博客内容
	private String blogsummary;//博客摘要
	private String remark;//备注
	private Date   addedTime;//新增时间
	public Long getPkid() {
		return pkid;
	}
	public void setPkid(Long pkid) {
		this.pkid = pkid;
	}
	public String getBlogtext() {
		return blogtext;
	}
	public void setBlogtext(String blogtext) {
		this.blogtext = blogtext;
	}
	public String getBloghref() {
		return bloghref;
	}
	public void setBloghref(String bloghref) {
		this.bloghref = bloghref;
	}
	public String getBlogcontent() {
		return blogcontent;
	}
	public void setBlogcontent(String blogcontent) {
		this.blogcontent = blogcontent;
	}
	public String getBlogsummary() {
		return blogsummary;
	}
	public void setBlogsummary(String blogsummary) {
		this.blogsummary = blogsummary;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getAddedTime() {
		return addedTime;
	}
	public void setAddedTime(Date addedTime) {
		this.addedTime = addedTime;
	}
	
}
