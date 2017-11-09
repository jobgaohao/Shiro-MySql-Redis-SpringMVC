package com.sojson.seckill.seckillOpportunity;

/**
 * 
 * <pre>
 * 基础验证
 * </pre>
 *
 * @author hao.gao
 * @version $Id: BaseValidate.java, v 0.1 2017年11月8日 下午3:36:33 hao.gao Exp $
 */
public interface BaseValidate<T> {

    /**
     * 
     * <pre>
     * 基础验证
     * </pre>
     *
     * @param t
     * @return
     * @throws Exception
     */
    public T doValidate(T t) throws Exception;
    
    /**
     * 
     * <pre>
     * 是否开启验证
     * </pre>
     *
     * @param b
     * @return
     * @throws Exception
     */
    public boolean openValidate(boolean b) throws Exception;
}
