package com.sojson.seckill.service;

import java.util.List;

import com.sojson.seckill.dto.Exposer;
import com.sojson.seckill.dto.SeckillExecution;
import com.sojson.seckill.exception.RepeatKillException;
import com.sojson.seckill.exception.SeckillCloseException;
import com.sojson.seckill.exception.SeckillException;
import com.sojson.seckill.model.Seckill;

public interface SeckillService {
    
    /**
     * 
     * <pre>
     * 查询全部秒杀记录
     * </pre>
     *
     * @return
     */
    List<Seckill> getSeckillList();
    
    /**
     * 
     * <pre>
     * 查询单个秒杀记录
     * </pre>
     *
     * @param seckillId
     * @return
     */
    Seckill getSeckillById(Long seckillId);

    /**
     * 
     * <pre>
     * 秒杀开启时候输出秒杀接口地址，否在输出系统时间和秒杀时间
     * </pre>
     *
     * @param seckillId
     * @return
     */
    Exposer exportSeckillUrl(Long seckillId);

    /**
     * 
     * <pre>
     * 执行秒杀操作，可能成功失败因此要抛出允许的异常
     * </pre>
     *
     * @param seckillId
     * @param userPhone
     * @param md5
     * @return
     * @throws SeckillException
     * @throws RepeatKillException
     * @throws SeckillCloseException
     */
    SeckillExecution executeSeckill(Long seckillId,Long userPhone,String md5) throws SeckillException,RepeatKillException,SeckillCloseException;

}
