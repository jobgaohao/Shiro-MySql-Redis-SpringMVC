package com.sojson.seckill.seckillOpportunity;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AbstractBaseValidate<T> {

    @Autowired
    private List<BaseValidate<T>> baseValidates;
    
    @Autowired
    private ValidateBroker<T> validateBroker;
    
         
    public T doValidate(T t) throws Exception{
        for(BaseValidate<T> baseValidate : baseValidates){
            if (baseValidate.openValidate(true)){
                validateBroker.putValidate(baseValidate);
            }
        }
        return (T)validateBroker.executeValidate(t);
    }
}
