package com.sojson.fitments.mapper;

import com.sojson.common.model.Fitment;
import com.sojson.fitments.bo.FitmentBo;

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
    
    /**
     * 修改装修日记
     * @param fitment
     * @return
     */
    int modifyFitment(Fitment fitment);
    
    /**
     * 根据PKID获取单个装修日记
     * @param id
     * @return
     */
    Fitment getOneFitmentByPkid(FitmentBo fitmentBo);
}

