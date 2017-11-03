package com.sojson.seckill.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sojson.seckill.dto.SeckillResult;
import com.sojson.seckill.model.Seckill;
import com.sojson.seckill.service.SeckillService;

@Component
@RequestMapping("/seckill")
public class SeckillController {

    @Autowired
    private SeckillService seckillService;
    
    @RequestMapping(value="/list",method=RequestMethod.GET)
    public String list(Model model){
        List<Seckill> list=seckillService.getSeckillList(); 
        model.addAttribute("list",list);
        return "list";
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
}
