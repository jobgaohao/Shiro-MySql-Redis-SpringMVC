package com.sojson.common.dao;

import com.sojson.common.model.Fitment;
import com.sojson.common.model.UPermission;

public interface FitmentMapper {
	
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
	int insertSelective(Fitment fitment);
}
