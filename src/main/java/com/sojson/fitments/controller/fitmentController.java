package com.sojson.fitments.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sojson.common.aopLog.SystemLog;
import com.sojson.common.controller.BaseController;
import com.sojson.common.model.CommonEnum;
import com.sojson.common.model.Fitment;
import com.sojson.common.utils.LoggerUtils;
import com.sojson.core.mybatis.page.Pagination;
import com.sojson.fitments.service.FitmentService;


/**
 * 装修日记
 * @author hao.gao
 *
 */
@Controller
@Scope(value="prototype")
@RequestMapping("/fitment")
public class fitmentController extends BaseController {
   @Autowired
   FitmentService fitmentService;
   
   /**
    * 装修日记列表
    * @param findContent
    * @param modelMap
    * @return
    */
   @SystemLog(value="查询装修列表")
   @RequestMapping(value="/index")
   public ModelAndView index(String findContent,ModelMap modelMap,Integer pageNo){
	modelMap.put("findContent", findContent);
	Pagination<Fitment> fitmentList=fitmentService.findPage(modelMap, pageNo, pageSize);
	return  new ModelAndView("fitment/list","page",fitmentList);   
   }
   
   /**
    * 删除装修日记
    * @param ids
    * @return
    */
   @RequestMapping(value="/delFitment",method=RequestMethod.POST)
   @ResponseBody
   public Map<String,Object> deleteFitment(String ids){
	   return fitmentService.deleteFitment(ids);
   }
   
   /**
    * 添加装修日记
    * @param fitment
    * @return
    */
   @RequestMapping(value="/addFitment",method=RequestMethod.POST)
   @ResponseBody
   public Map<String,Object> addFitment(Fitment fitment){
	   try {
		    if(null!=fitment){
		    	fitment.setAddedTime(new Date());
		    	fitment.setValid(CommonEnum.Valid.getValue());
		    	Fitment entity=fitmentService.insertSelective(fitment);
				resultMap.put("status",200);
				resultMap.put("entoty",entity);
		    }
		    else{
		    	resultMap.put("status",300);
		    	resultMap.put("message","请输入参数");		    	
		    }
		} catch (Exception e) {
			// TODO: handle exception
		    resultMap.put("status", 500);
		    resultMap.put("message","添加失败，请刷新后再试");
		    LoggerUtils.fmtError(getClass(), e, "添加装修日记报错。source[%s]", fitment.toString());
		}
	   return resultMap;
   }
   
   /**
    * 获取单个装修日记
    * @return
    */
   @RequestMapping(value="/getFitment",produces="application/json;charset=UTF-8")
   @ResponseBody
   public Map<String,Object> getFitment(String pkid)
   {
	  Fitment fitment=fitmentService.getOneByPkid(Long.parseLong(pkid));
	  if(null!=fitment){
		  resultMap.put("status",200);
		  resultMap.put("entity",fitment);
	  }
	  else{
		  resultMap.put("status",300);
		  resultMap.put("message","加载装修日记失败");
	  }
	  return resultMap;
   }
   
   /**
    * 修改装修日记
    * @param fitment
    * @return
    */
   @RequestMapping(value="/modifyFitment",method=RequestMethod.POST)
   @ResponseBody
   public Map<String,Object> modifyFitment(@ModelAttribute Fitment fitment)
   {
	   try {   
		    if(null!=fitment){
		    	resultMap=fitmentService.modifyFitment(fitment);
		    }
		    else{
		    	resultMap.put("status",300);
		    	resultMap.put("message","请刷新页面，重新操作");		    	
		    }
		} catch (Exception e) {
			// TODO: handle exception
		    resultMap.put("status", 500);
		    resultMap.put("message","修改失败，请刷新后再试");
		    LoggerUtils.fmtError(getClass(), e, "修改装修日记报错。source[%s]", fitment.toString());
		}
	   return resultMap;
   }
}
