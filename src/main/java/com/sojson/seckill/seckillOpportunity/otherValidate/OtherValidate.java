package com.sojson.seckill.seckillOpportunity.otherValidate;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.sojson.seckill.dto.SeckillInfo;
import com.sojson.seckill.seckillOpportunity.BaseValidate;
import com.sojson.seckill.utils.Utils;

/**
 * 
 * <pre>
 * 其他秒杀产品概率  十分之六【除去iPhone、iPad】
 * </pre>
 *
 * @author hao.gao
 * @version $Id: OtherValidate.java, v 0.1 2017年11月8日 下午6:27:59 hao.gao Exp $
 */
@Component
@Order(4)
public class OtherValidate<T> implements BaseValidate<T> {

    @Override
    public T doValidate(T t) throws Exception {
        Assert.isTrue(t instanceof SeckillInfo,"未知的参数类型");
        SeckillInfo seckillInfo=(SeckillInfo)t;
        String seckillObject=seckillInfo.getSeckill().getName();
        if(!(seckillObject.toLowerCase().indexOf("ipone")>0||
           seckillObject.toLowerCase().indexOf("ipad")>0)){
            seckillInfo.setHasSeckillOpportunity(Utils.isInOpportunity(4));
        }
        return t;
    }

    @Override
    public boolean openValidate(boolean b) throws Exception {
        return true;
    }

}
