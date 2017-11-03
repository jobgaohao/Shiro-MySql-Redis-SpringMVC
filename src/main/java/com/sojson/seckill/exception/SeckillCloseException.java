package com.sojson.seckill.exception;

/**
 * 
 * <pre>
 * 秒杀关闭异常
 * </pre>
 *
 * @author hao.gao
 * @version $Id: SeckillCloseException.java, v 0.1 2017年11月2日 下午4:34:12 hao.gao Exp $
 */
public class SeckillCloseException extends RuntimeException{
   public SeckillCloseException(String mes){
       super(mes);
   }
   
   public SeckillCloseException(String mes,Throwable cause)
   {
       super(mes,cause);
   }
}
