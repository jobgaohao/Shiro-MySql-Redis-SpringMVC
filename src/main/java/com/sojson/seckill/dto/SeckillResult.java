package com.sojson.seckill.dto;

/**
 * 
 * <pre>
 * 将所有的ajax请求返回的类型全部封装成json数据
 * </pre>
 *
 * @author hao.gao
 * @version $Id: SeckillResult.java, v 0.1 2017年11月2日 下午5:50:03 hao.gao Exp $
 */
public class SeckillResult<T>{

    private boolean success;
    private T data;
    private String error;
    
    public SeckillResult(boolean success,T data){
        this.success=success;
        this.data=data;
    }
    
    public SeckillResult(boolean success,String error){
        this.success=success;
        this.error=error;        
    }
    
    public boolean isSuccess() {
        return success;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
    public String getError() {
        return error;
    }
    public void setError(String error) {
        this.error = error;
    }
      
      
}
