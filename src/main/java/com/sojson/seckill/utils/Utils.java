package com.sojson.seckill.utils;

import java.util.Random;

public  class Utils {

    /**
     * 
     * <pre>
     * 是否在概率内
     * </pre>
     *
     * @param opportunityNum
     * @return
     */
    public static boolean isInOpportunity(int opportunityNum){
        Random rnd = new Random();
        int tempInt = rnd.nextInt(10);
        if(tempInt>=opportunityNum){
            return true;
        }
        return false;
    }
    
    /**
     * 
     * <pre>
     * 随机生成指定号段的手机号码
     * </pre>
     *
     * @return
     */
    public static Long getUserPhone(String phoneBegin){       
        StringBuilder sb=new StringBuilder();
        sb.append(phoneBegin);
        Random rnd=new Random();
        for (int i = 0; i < 8; i++) {
            sb.append(rnd.nextInt(10));
        }
        return Long.parseLong(sb.toString());
    }
}
