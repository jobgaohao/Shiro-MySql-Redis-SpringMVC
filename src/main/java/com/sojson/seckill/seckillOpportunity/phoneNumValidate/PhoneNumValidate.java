package com.sojson.seckill.seckillOpportunity.phoneNumValidate;


import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.sojson.seckill.dto.SeckillInfo;
import com.sojson.seckill.seckillOpportunity.BaseValidate;
import com.sojson.seckill.utils.Utils;

/**
 * 
 * <pre>
 * 秒杀概率：手机号码尾号机会
 * 手机号尾号为88 的所有秒杀都为二分之一概率
 * </pre>
 *
 * @author hao.gao
 * @version $Id: PhoneNumValidate.java, v 0.1 2017年11月8日 下午4:21:22 hao.gao Exp $
 */
@Component
@Order(1)
public class PhoneNumValidate<T> implements BaseValidate<T> {

    @Override
    public T doValidate(T t) throws Exception {
        Assert.isTrue(t instanceof SeckillInfo,"未知的参数类型");
        SeckillInfo seckillInfo=(SeckillInfo)t;
        String userPhone=seckillInfo.getUserPhone().toString();
        if("88".equals(userPhone.substring(9))){
            seckillInfo.setHasSeckillOpportunity(Utils.isInOpportunity(5));
        }
        return t;
    }

    @Override
    public boolean openValidate(boolean b) throws Exception {
        return false;
    }

}
