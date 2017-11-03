package com.sojson.seckill.model;

import java.util.Date;

/**
 * 
 * <pre>
 * 成功秒杀对象
 * </pre>
 *
 * @author hao.gao
 * @version $Id: SuccessKilled.java, v 0.1 2017年11月2日 下午5:05:22 hao.gao Exp $
 */
public class SuccessKilled {
    private Long seckillId;
    private String userPhone;
    private Integer state;
    private Date createTime;
    private Seckill seckill;
    
    public Long getSeckillId() {
        return seckillId;
    }
    public void setSeckillId(Long seckillId) {
        this.seckillId = seckillId;
    }
    public String getUserPhone() {
        return userPhone;
    }
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
    public Integer getState() {
        return state;
    }
    public void setState(Integer state) {
        this.state = state;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Seckill getSeckill() {
        return seckill;
    }
    public void setSeckill(Seckill seckill) {
        this.seckill = seckill;
    }       
}
