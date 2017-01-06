package com.sojson.fitments.bo;

/**
 * 查询条件
 * @author hao.gao
 *
 */
public class FitmentBo {
   
	/**
	 * 查询条件
	 */
	public String findContent;
	
	/**
	 * pkid
	 */
	public Long Pkid;

	public String getFindContent() {
		return findContent;
	}

	public void setFindContent(String findContent) {
		this.findContent = findContent;
	}

	public Long getPkid() {
		return Pkid;
	}

	public void setPkid(Long pkid) {
		Pkid = pkid;
	}
	
}
