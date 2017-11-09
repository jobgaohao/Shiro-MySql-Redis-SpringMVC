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
}
