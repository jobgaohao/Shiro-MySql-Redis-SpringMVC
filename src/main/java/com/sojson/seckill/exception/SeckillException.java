package com.sojson.seckill.exception;

/**
 * 
 * <pre>
 * 秒杀业务所有的异常
 * </pre>
 *
 * @author hao.gao
 * @version $Id: SeckillException.java, v 0.1 2017年11月2日 下午3:34:14 hao.gao Exp $
 */
public class SeckillException extends RuntimeException {

    public SeckillException (String message){
        super(message);
    }
    
    public SeckillException (String message,Throwable cause){
        super(message,cause);
    }
}
