package com.sojson.fitments.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sojson.common.dao.FitmentMapper;
import com.sojson.common.dao.UPermissionMapper;
import com.sojson.common.model.Fitment;
import com.sojson.common.utils.LoggerUtils;
import com.sojson.common.utils.StringUtils;
import com.sojson.core.mybatis.BaseMybatisDao;
import com.sojson.core.mybatis.page.Pagination;
import com.sojson.fitments.bo.FitmentBo;
import com.sojson.fitments.service.FitmentService;
@Service
public class FitmentServiceImpl extends BaseMybatisDao<FitmentMapper> implements FitmentService {

	@Autowired
	FitmentMapper fimentMapper;
	
	/**
	 * 分页查询
	 */
	@Override
	public Pagination<Fitment> findPage(Map<String, Object> resultMap,
			Integer pageNo, Integer pageSize) {
		Pagination<Fitment> fitmentList=  super.findPage(resultMap, pageNo, pageSize);
		return fitmentList;
	}

	/**
	 * 删除
	 */
	@Override
	public Map<String, Object> deleteFitment(String ids) {
		//region 删除逻辑
		Map<String,Object> resultMap=new HashMap<String,Object>();
		try {
			int successCount=0;
			String resultMsg ="删除%s条，失败%s条";
			String[] idArray=new String[]{};
			if(StringUtils.contains(ids,",")){
				idArray=ids.split(",");
			}
			else{
				idArray=new String[]{ids};
			}
			
			for (String id : idArray) {
				successCount+=fimentMapper.deleteByPrimaryKey(Long.parseLong(id));
			}
			
			//成功
			if(successCount==idArray.length){
				resultMap.put("status", 200);
				resultMap.put("resultMsg", "操作成功");
			}
			else{
				resultMap.put("status", 200);				
				resultMsg = String.format(resultMsg, successCount,(idArray.length-successCount));
				resultMap.put("resultMsg", resultMsg);
			}
		} catch (Exception e) {
			// TODO: handle exception
			LoggerUtils.fmtError(getClass(), e, "根据IDS删除用户出现错误，ids[%s]", ids);
			resultMap.put("status", 500);
			resultMap.put("message", "删除出现错误，请刷新后再试！");
		}
		//endregion
		return resultMap;		
	}

	/**
	 * 添加查询
	 */
	@Override
	public Fitment insertSelective(Fitment record) {
		// TODO Auto-generated method stub
		fimentMapper.insertSelective(record);
		return record;
	}
    
	/**
	 * 查询单个
	 */
	@Override
	public Fitment getOneByPkid(Long pkid) {
		FitmentBo fitmentBo=new FitmentBo();
		fitmentBo.setPkid(pkid);
		Fitment fitment=fimentMapper.getOneFitmentByPkid(fitmentBo);
		return fitment;
	}
    
	/**
	 * 修改
	 */
	@Override
	public Map<String, Object> modifyFitment(Fitment fitment) {
		Map<String,Object> resultMap=new HashMap<String, Object>();
		try {
			int result= fimentMapper.modifyFitment(fitment);
			if(result>0){
				resultMap.put("status", 200);
				resultMap.put("message", "修改成功");
			}
			else{
				resultMap.put("status", 300);
				resultMap.put("message", "修改失败，请刷新后重试");
			}
		} catch (Exception e) {
			resultMap.put("status", 500);
			resultMap.put("message", "修改失败，请刷新后重试");
			LoggerUtils.fmtError(getClass(), e, "修改装修日记失败[%s]", fitment);
		}
		return resultMap;
	}

}
