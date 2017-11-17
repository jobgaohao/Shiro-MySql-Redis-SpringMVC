package com.sojson.seckill.seckillOpportunity.iphoneValidate;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.sojson.seckill.dto.SeckillInfo;
import com.sojson.seckill.seckillOpportunity.BaseValidate;
import com.sojson.seckill.utils.Utils;

/**
 * 
 * <pre>
 * iphone 手机 十分之一的概率
 * </pre>
 *
 * @author hao.gao
 * @version $Id: IPhoneValidate.java, v 0.1 2017年11月8日 下午6:22:42 hao.gao Exp $
 */
@Component
@Order(2)
public class IPhoneValidate<T> implements BaseValidate<T> {

    @Override
    public T doValidate(T t) throws Exception {
        Assert.isTrue(t instanceof SeckillInfo,"未知的参数类型");
        SeckillInfo seckillInfo=(SeckillInfo)t;
        String seckillObject=seckillInfo.getSeckill().getName();
        if(seckillObject.toLowerCase().indexOf("iphone")>0){
            seckillInfo.setHasSeckillOpportunity(Utils.isInOpportunity(9));
        }
        return t;
    }

    @Override
    public boolean openValidate(boolean b) throws Exception {
        return false;
    }

}
