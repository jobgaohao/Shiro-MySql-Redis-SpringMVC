package com.sojson.seckill.seckillOpportunity.ipadValidate;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.sojson.seckill.dto.SeckillInfo;
import com.sojson.seckill.seckillOpportunity.BaseValidate;
import com.sojson.seckill.utils.Utils;

/**
 * 
 * <pre>
 * iPad 十分之四的概率
 * </pre>
 *
 * @author hao.gao
 * @version $Id: IPadValidate.java, v 0.1 2017年11月8日 下午6:26:33 hao.gao Exp $
 */
@Component
@Order(3)
public class IPadValidate<T> implements BaseValidate<T> {

    @Override
    public T doValidate(T t) throws Exception {
        Assert.isTrue(t instanceof SeckillInfo,"未知的参数类型");
        SeckillInfo seckillInfo=(SeckillInfo)t;
        String seckillObject=seckillInfo.getSeckill().getName();
        if(seckillObject.toLowerCase().indexOf("ipad")>0){
            seckillInfo.setHasSeckillOpportunity(Utils.isInOpportunity(6));
        }
        return t;
    }

    @Override
    public boolean openValidate(boolean b) throws Exception {
        return false;
    }

}
