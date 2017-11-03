package com.sojson.seckill.enums;

/**
 * 
 * <pre>
 * 秒杀枚举
 * </pre>
 *
 * @author hao.gao
 * @version $Id: EnumSeckillState.java, v 0.1 2017年11月2日 下午3:17:49 hao.gao Exp $
 */
public enum EnumSeckillState {
       SUCCESS(1,"秒杀成功"),
       END(0,"秒杀结束"),
       REPEAT_KILL(-1,"重复秒杀"),
       INNER_ERROR(-2,"系统异常"),
       DATE_REWRITE(-3,"数据篡改");
       
       private int state;
       public int getState() {
        return state;
       } 

       private String info;
       public String getInfo() {
            return info;
       }
                 
       EnumSeckillState(int state,String info){
           this.state=state;
           this.info=info;
       }
       
       /**
        * 
        * <pre>
        * 根据秒杀状态获取秒杀枚举类
        * </pre>
        *
        * @param state
        * @return
        */
       public static EnumSeckillState getInfoByState(int state){
           for (EnumSeckillState info : values()) {
            if(info.getState()==state){
                return info;
            }
           }
           return null;
       }
}
