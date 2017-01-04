package com.sojson.common.model;

import java.io.Serializable;
import java.util.Date;

import net.sf.json.JSONObject;

/**
 * 装修日记
 * @author hao.gao
 *
 */
public class Fitment implements Serializable {
	
    private Long id;
    private String title;
    private String content;
    private String tag;
    private String star;
    private String style;
    private String remark;
    private String url;
    private Date addTime;
    private int valid;
    
    
	public Long getid() {
		return id;
	}
	public void setPkid(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getStar() {
		return star;
	}
	public void setStar(String star) {
		this.star = star;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public int getValid() {
		return valid;
	}
	public void setValid(int valid) {
		this.valid = valid;
	}
    
	
	public String toString(){
		return JSONObject.fromObject(this).toString();
	}
}
