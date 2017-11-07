package com.sojson.seckill.dto;

import com.sojson.seckill.enums.EnumSeckillState;
import com.sojson.seckill.model.SuccessKilled;

/**
 * 
 * <pre>
 * 封装执行秒杀后的结果：是否秒杀成功
 * </pre>
 *
 * @author hao.gao
 * @version $Id: SeckillExecution.java, v 0.1 2017年11月2日 下午5:31:53 hao.gao Exp $
 */
public class SeckillExecution {

    private Long seckillId;
    
    //秒杀执行结果的状态
    private Integer state;
    
    //秒杀执行结果的明文标识
    private String stateInfo;
    
    //秒杀成功对象
    private SuccessKilled successKilled;

    //秒杀成功返回所有的信息
    public SeckillExecution(Long seckillId,EnumSeckillState enumSeckillState,SuccessKilled SuccessKilled){        
        this.seckillId=seckillId;
        this.state=enumSeckillState.getState();
        this.stateInfo=enumSeckillState.getInfo();
        this.successKilled=successKilled;
    }
    
    //秒杀失败后的信息
    public SeckillExecution(Long seckillId,EnumSeckillState enumSeckillState){
        this.seckillId=seckillId;
        this.state=enumSeckillState.getState();
        this.stateInfo=enumSeckillState.getInfo();
    }
    
    public Long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(Long seckillId) {
        this.seckillId = seckillId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public SuccessKilled getSuccessKilled() {
        return successKilled;
    }

    public void setSuccessKilled(SuccessKilled successKilled) {
        this.successKilled = successKilled;
    }
    
    
}
