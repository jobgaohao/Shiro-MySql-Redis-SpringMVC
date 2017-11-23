package com.sojson.seckill.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sojson.common.aopLog.SystemLog;
import com.sojson.common.controller.BaseController;
import com.sojson.seckill.dto.Exposer;
import com.sojson.seckill.dto.SeckillExecution;
import com.sojson.seckill.dto.SeckillResult;
import com.sojson.seckill.enums.EnumSeckillState;
import com.sojson.seckill.exception.RepeatKillException;
import com.sojson.seckill.exception.SeckillCloseException;
import com.sojson.seckill.exception.SeckillException;
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
    @RequestMapping(value="/time/now",method=RequestMethod.GET)
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
    @SystemLog("获取秒杀商品明细")
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
        seckill.setStartTimeLong(seckill.getStartTime().getTime());
        seckill.setEndTimeLong(seckill.getEndTime().getTime());
        model.addAttribute("seckill",seckill);
        return redirect;
    }
    
    /**
     * 
     * <pre>
     * Ajax暴露秒杀接口
     * </pre>
     *
     * @param seckillId
     * @return
     */
    @SystemLog("Ajax暴露秒杀接口")
    @RequestMapping(value="/{seckillId}/exposer",method=RequestMethod.GET,produces={"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<Exposer> exposer(@PathVariable("seckillId") Long seckillId){
        SeckillResult<Exposer> result;
        try {
            Exposer exposer=seckillService.exportSeckillUrl(seckillId);
            result=new SeckillResult<Exposer>(true, exposer);
        } catch (Exception e) {
            e.printStackTrace();
            result=new SeckillResult<Exposer>(false,e.getMessage());
        }
        return result;
    }
    
    /**
     * 
     * <pre>
     * 执行秒杀
     * </pre>
     *
     * @param seckillId
     * @param md5
     * @param userPhone
     * @return
     */
    @SystemLog("执行秒杀")
    @RequestMapping(value="/{seckillId}/{md5}/execution",
            method=RequestMethod.POST,
            produces={"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<SeckillExecution> execute(@PathVariable("seckillId") Long seckillId,@PathVariable("md5") String md5,@CookieValue(value="userPhone",required=false) Long userPhone){
        if(userPhone==null){
            return new SeckillResult<SeckillExecution>(false,"未注册");
        }       
        try {
            SeckillExecution execution=seckillService.executeSeckill(seckillId, userPhone, md5);
            return new SeckillResult<SeckillExecution>(true, execution);
        }
        catch (RepeatKillException e1){
            SeckillExecution execution=new SeckillExecution(seckillId,EnumSeckillState.REPEAT_KILL);
            return new SeckillResult<SeckillExecution>(true,execution);
        }
        catch(SeckillCloseException e2){
            SeckillExecution execution=new SeckillExecution(seckillId,EnumSeckillState.END);
            return new SeckillResult<SeckillExecution>(true,execution);
        }
        catch(SeckillException e3){
            SeckillExecution execution=new SeckillExecution(seckillId,EnumSeckillState.BAD_OPPORTUNITY);
            return new SeckillResult<SeckillExecution>(true,execution);
        }
        catch (Exception e) {
            SeckillExecution execution=new SeckillExecution(seckillId,EnumSeckillState.INNER_ERROR);
            return new SeckillResult<SeckillExecution>(true,execution);
        }
    }

    @SystemLog("通过程序模拟大并发秒杀")
    @RequestMapping(value="/{seckillId}/{threadCount}/execuByMachine")
    @ResponseBody
    public void executeByMachine(@PathVariable("seckillId") Long seckillId,@PathVariable("threadCount") Integer threadCount){  
       try {
           //seckillService.startThreadSeckill(threadCount,seckillId); 
           //seckillService.startThreadSeckillOne(threadCount,seckillId); 
           seckillService.startThreadSeckillTwo(3,seckillId);
        } catch (Exception e) {
           e.printStackTrace();
        }
               
    }
}
