package com.sojson.common.aopLog;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * 定义一个切点类
 * <pre>
 * 
 * </pre>
 *
 * @author hao.gao
 * @version $Id: LoggerAspect.java, v 0.1 2017年5月8日 上午10:55:15 hao.gao Exp $
 */
@Aspect
@Order(1)
@Component
public class LoggerAspect {

    //Controller层切点  
    @Pointcut("@annotation(com.sojson.common.aopLog.SystemLog)")
    public void methodAll(){}

    //前置通知 用于拦截Controller层记录用户的操作  
    @Before("methodAll()")
    public void logBefore(JoinPoint joinPoint){
        logAop(joinPoint, true);
    }
    
    /**
     * 后置通知
     * <pre>
     * 
     * </pre>
     *
     * @param joinPoint：切点
     */
    @After("methodAll()")
    public void logAfger(JoinPoint joinPoint){
        logAop(joinPoint, false);
    }
    
    
    private void logAop(JoinPoint joinPoint ,boolean needArgs){
        try {
            String targetName = joinPoint.getTarget().getClass().getName();    
            String methodName = joinPoint.getSignature().getName();    
            Object[] arguments = joinPoint.getArgs();    
            Class targetClass = Class.forName(targetName);    
            Method[] methods = targetClass.getMethods();    
            String description = "";    
             for (Method method : methods) {    
                 if (method.getName().equals(methodName)) {    
                    Class[] clazzs = method.getParameterTypes();    
                     if (clazzs.length == arguments.length) {    
                        description = method.getAnnotation(SystemLog.class).toString();    
                        printLog(description);  
                    }    
                }    
            }    
        } catch (Exception e) {
            // TODO: handle exception
        }
        
    }

    /**
     * 
     * <pre>
     * 打印日志
     * </pre>
     *
     * @param topic
     * @param title
     * @param message
     */
    private void printLog(String message){
        System.out.println("message:"+message);
    }
}
