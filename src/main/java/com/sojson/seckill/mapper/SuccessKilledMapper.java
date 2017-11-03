package com.sojson.seckill.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import com.sojson.seckill.model.SuccessKilled;

@Component("SuccessKilledMapper")
public interface SuccessKilledMapper {

    /**
     * 
     * <pre>
     * 插入秒杀购买明细，可以过滤重复
     * </pre>
     *
     * @param seckillId
     * @param userPhone
     * @return
     */
    Integer insertSuccessKilled(@Param("seckillId") Long seckillId,@Param("userPhone") Long userPhone);

    /**
     * 
     * <pre>
     * 根据秒杀商品的ID查询SuccessKilled对象
     * </pre>
     *
     * @param seckillId
     * @param userPhone
     * @return
     */
    SuccessKilled queryByIdWithSeckill(@Param("seckillId") Long seckillId,@Param("userPhone") Long userPhone);
}
