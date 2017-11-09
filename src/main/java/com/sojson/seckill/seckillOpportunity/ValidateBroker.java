package com.sojson.seckill.seckillOpportunity;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

import org.springframework.stereotype.Component;

/**
 * 
 * <pre>
 * 命令类【命令模式】
 * </pre>
 *
 * @author hao.gao
 * @version $Id: ValidateBroker.java, v 0.1 2017年11月8日 下午3:41:27 hao.gao Exp $
 */
@Component
public class ValidateBroker<T> {

    private Queue<BaseValidate<T>> validates=new ConcurrentLinkedDeque<BaseValidate<T>>();
    
    public void putValidate(BaseValidate<T> validate){
        validates.add(validate);
    }
    
    
    public T executeValidate(T t) throws Exception{
        for (BaseValidate<T> baseValidate : validates) {
            baseValidate.doValidate(t);
        }
        validates.clear();
        return t;
    }
}
