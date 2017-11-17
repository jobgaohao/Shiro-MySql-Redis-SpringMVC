package com.sojson.seckill.seckillOpportunity;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AbstractBaseValidate<T> {

    private final boolean OPEN_VALIDATE=true;//是否打开记录验证
    
    @Autowired
    private List<BaseValidate<T>> baseValidates;
        
         
    public T doValidate(T t) throws Exception{
        ValidateBroker<T> validateBroker=new ValidateBroker<T>();        
        for(BaseValidate<T> baseValidate : baseValidates){ 
            boolean isOpenBaseValidate=baseValidate.openValidate(OPEN_VALIDATE);
            if (isOpenBaseValidate){
                validateBroker.putValidate(baseValidate);
            }
        }
        return (T)validateBroker.executeValidate(t);
    }
}
