package com.sojson.fitments.service;

import java.util.Map;

import com.sojson.common.model.Fitment;
import com.sojson.common.model.UPermission;
import com.sojson.core.mybatis.page.Pagination;

public interface FitmentService {
	/**
	 * 分页查询
	 * @param resultMap
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	Pagination<Fitment> findPage(Map<String, Object> resultMap, Integer pageNo,
			Integer pageSize);
	
	
	/**
	 * 删除装修日记
	 * @param ids
	 * @return
	 */
	Map<String, Object> deleteFitment(String ids);
	
	/**
	 * 添加装修日记
	 * @param record
	 * @return
	 */
	Fitment insertSelective(Fitment record);
	
	/**
	 * 根据PKID获取单个装修日记
	 * @param pkid
	 * @return
	 */
	Fitment getOneByPkid(Long pkid);
	
	/**
	 * 修改装修日记
	 * @param fitment
	 * @return
	 */
	Map<String,Object> modifyFitment(Fitment fitment);
}
