package com.sojson.seckill.dto;

import org.springframework.stereotype.Component;

import com.sojson.seckill.model.Seckill;

/**
 * 
 * <pre>
 * 秒杀关键对象
 * </pre>
 *
 * @author hao.gao
 * @version $Id: SeckillInfo.java, v 0.1 2017年11月8日 下午4:35:47 hao.gao Exp $
 */
@Component
public class SeckillInfo {
    //是否有秒杀机会
    private boolean hasSeckillOpportunity=false;
    
    //秒杀用户手机号
    private Long userPhone;
    
    //秒杀对象
    private Seckill seckill;
           
    public boolean isHasSeckillOpportunity() {
        return hasSeckillOpportunity;
    }
    public void setHasSeckillOpportunity(boolean hasSeckillOpportunity) {
        this.hasSeckillOpportunity = hasSeckillOpportunity;
    }
    public Long getUserPhone() {
        return userPhone;
    }
    public void setUserPhone(Long userPhone) {
        this.userPhone = userPhone;
    }
    public Seckill getSeckill() {
        return seckill;
    }
    public void setSeckill(Seckill seckill) {
        this.seckill = seckill;
    }
    
}
