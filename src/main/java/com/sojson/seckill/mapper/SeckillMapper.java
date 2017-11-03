package com.sojson.seckill.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.sojson.seckill.model.Seckill;

@Component("SeckillMapper")
public interface SeckillMapper {

    /**
     * 
     * <pre>
     * 减库存
     * </pre>
     *
     * @param seckillId
     * @param killTime
     * @return
     */
    Integer reduceNumber(@Param("seckillId") Long seckillId,
                         @Param("killTime") Date killTime);
    
    /**
     * 
     * <pre>
     * 根据ID查询秒杀商品
     * </pre>
     *
     * @param seckillId
     * @return
     */
    Seckill queryBySeckillId(Long seckillId);
    
    /**
     * 
     * <pre>
     * 分页查询秒杀列表
     * </pre>
     *
     * @param offset
     * @param limit
     * @return
     */
    List<Seckill> querySeckillList(@Param("offset") int offset,@Param("limit") int limit);
}
