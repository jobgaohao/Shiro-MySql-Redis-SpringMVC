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
 * 在Spring AOP中，切面可以使用通用类或者在普通类中以@Aspect 注解（@AspectJ风格）来实现
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

    /**
     * 前置通知 用于拦截Controller层记录用户的操作  
     * <pre>
     * @Before 在某连接点（join point）之前执行的通知
     * 但这个通知不能阻止连接点前的执行（除非它抛出一个异常）
     * </pre>
     *
     * @param joinPoint
     */
    @Before("methodAll()")
    public void logBefore(JoinPoint joinPoint){
        logAop(joinPoint);
    }
    
    /**
     * 后置通知
     * <pre>
     * 在切面的某个特定的连接点（Joinpoint）上执行的动作。通知有各种类型，其中包括"around"、"before”和"after"等通知。
     * 许多AOP框架，包括Spring，都是以拦截器做通知模型， 并维护一个以连接点为中心的拦截器链
     * </pre>
     *
     * <pre>
     * @After在某连接点（join point）正常完成后执行的通知
     * </pre>
     * 
     * <pre>
     * 环绕通知（@Around）：包围一个连接点（join point）的通知，如方法调用。这是最强大的一种通知类型，
     * 环绕通知可以在方法调用前后完成自定义的行为，它也会选择是否继续执行连接点或直接返回它们自己的返回值或抛出异常来结束执行
     * </pre>
     * @param joinPoint：切点
     */
    @After("methodAll()")
    public void logAfger(JoinPoint joinPoint){
        logAop(joinPoint);
    }
    
    /**
     * 
     * <pre>
     * 连接点（Joinpoint） 在Spring AOP中一个连接点代表一个方法的执行
     * </pre>
     *
     * @param joinPoint
     * @param needArgs
     */
    private void logAop(JoinPoint joinPoint){
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
             
            for (int i = 0; i < arguments.length; i++) {
              System.out.println("参数："+arguments[i]);
                
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
