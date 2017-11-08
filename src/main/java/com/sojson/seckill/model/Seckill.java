package com.sojson.seckill.model;

import java.util.Date;

/**
 * 
 * <pre>
 * 秒杀物品对象
 * </pre>
 *
 * @author hao.gao
 * @version $Id: seckill.java, v 0.1 2017年11月2日 下午5:04:26 hao.gao Exp $
 */
public class Seckill {
    
    private Long seckillId;
    private String name;
    private Integer number;
    private Date startTime;
    private Date endTime;
    private Date createTime;
    private Long startTimeLong;
    private Long endTimeLong;
    
    
    public Long getSeckillId() {
        return seckillId;
    }
    public void setSeckillId(Long seckillId) {
        this.seckillId = seckillId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getNumber() {
        return number;
    }
    public void setNumber(Integer number) {
        this.number = number;
    }
    public Date getStartTime() {
        return startTime;
    }
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    public Date getEndTime() {
        return endTime;
    }
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Long getStartTimeLong() {
        return startTimeLong;
    }
    public void setStartTimeLong(Long startTimeLong) {
        this.startTimeLong = startTimeLong;
    }
    public Long getEndTimeLong() {
        return endTimeLong;
    }
    public void setEndTimeLong(Long endTimeLong) {
        this.endTimeLong = endTimeLong;
    }
    
    
}
