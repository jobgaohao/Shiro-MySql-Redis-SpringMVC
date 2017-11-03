package com.sojson.seckill.exception;

/**
 * 
 * <pre>
 * 重复秒杀异常
 * </pre>
 *
 * @author hao.gao
 * @version $Id: RepeatKillException.java, v 0.1 2017年11月2日 下午4:33:28 hao.gao Exp $
 */
public class RepeatKillException extends RuntimeException {

    public RepeatKillException (String mes){
        super(mes);
    }
    
    public RepeatKillException (String mes,Throwable cause){
        super(mes,cause);
    }
}
