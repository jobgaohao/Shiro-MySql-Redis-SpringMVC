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
    SeckillExecution executeSeckill(Long seckillId,Long userPhone,String md5) throws Exception;

    /**
     * 
     * <pre>
     * 通过程序测试秒杀
     * </pre>
     *
     * @param seckillId:秒杀对象ID
     * @param userPhone:秒杀用户电话
     * @return
     * @throws SeckillException
     * @throws RepeatKillException
     * @throws SeckillCloseException
     */
    void addSeckillByMachine(Long seckillId,Long userPhone) throws Exception;
   
    /**
     * 
     * <pre>
     * 开启指定线程的秒杀
     * </pre>
     *
     * @param threadCount
     * @param seckillId
     */
    void startThreadSeckill(Integer threadCount,Long seckillId);      

    /**
     * 
     * <pre>
     * 进行秒杀测试
     * a.线程1进行存放秒杀信息
     * b.线程2进行秒杀操作
     * </pre>
     *
     * @param threadCount
     * @param seckillId
     */
    void startThreadSeckillOne(Integer threadCount,Long seckillId) throws Exception;

    /**
     * 
     * <pre>
     * 进行秒杀测试
     * a.开启三个线程
     * b.三个线程都准备好后进行秒杀     
     * </pre>
     *
     * @param threadCount
     * @param seckillId
     * @throws Exception
     */
    void startThreadSeckillTwo(Integer threadCount,Long seckillId) throws Exception;
    
    /**
     * 
     * <pre>
     * 测试 spring task by @Scheduled
     * </pre>
     *
     */
    void testScheduled();  
}
