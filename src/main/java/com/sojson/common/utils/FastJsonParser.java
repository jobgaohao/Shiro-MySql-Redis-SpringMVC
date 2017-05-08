package com.sojson.common.utils;

import java.util.List;

import com.alibaba.fastjson.JSON;

public class FastJsonParser {
    public static String toJson(Object token) {
        return JSON.toJSONString(token);
    }

    public static <T> T parseObject(String text, Class<? extends T> clazz) {
        return JSON.parseObject(text, clazz);
    }
    
    public static <T> List<T>  parseArray(String text, Class<? extends T> clazz){
        return (List<T>) JSON.parseArray(text, clazz);
    }
}
