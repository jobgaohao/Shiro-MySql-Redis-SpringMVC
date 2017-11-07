package com.sojson.seckill.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sojson.common.controller.BaseController;
import com.sojson.seckill.dto.SeckillResult;
import com.sojson.seckill.model.Seckill;
import com.sojson.seckill.service.SeckillService;

@Component
@RequestMapping("/seckill")
public class SeckillController extends BaseController {

    @Autowired
    private SeckillService seckillService;
    
    /**
     * 
     * <pre>
     * 列表页面
     * </pre>
     *
     * @param findContent
     * @param pageNo
     * @return
     */
    @RequestMapping(value="/index",method=RequestMethod.GET)
    public ModelAndView index(String findContent,Integer pageNo){
        Map<String,Object> map=new HashMap<String,Object>();
        List<Seckill> list=seckillService.getSeckillList(); 
        map.put("page", list);
        return new ModelAndView("seckill/list",map);
    }
    
    /**
     * 
     * <pre>
     * 获取系统时间
     * </pre>
     *
     * @return
     */
    @RequestMapping(value="/timeNow",method=RequestMethod.GET)
    @ResponseBody
    public SeckillResult<Long> time(){
        Date now=new Date();
        return new SeckillResult<Long>(true, now.getTime());
    }
    
    /**
     * 
     * <pre>
     * 获取秒杀商品明细
     * </pre>
     *
     * @param seckillId
     * @param model
     * @return
     */
    @RequestMapping(value = "/{seckillId}/detail",method = RequestMethod.GET)
    public String detail(@PathVariable("seckillId") Long seckillId, Model model){
        String redirect="/seckill/detail";
        if(seckillId==null){
            redirect="redirect:/seckill/list";
            return redirect;
        }        
        Seckill seckill=seckillService.getSeckillById(seckillId);
        if(seckill==null){
            redirect="forward:/seckill/list";
            return redirect;
        }
        model.addAttribute("seckill",seckill);
        return redirect;
    }
}
