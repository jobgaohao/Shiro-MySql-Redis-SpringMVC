package com.sojson.core.shiro.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;

import com.sojson.common.utils.LoggerUtils;
import com.sojson.common.utils.SerializeUtil;

/**
 * 
 * 开发公司：SOJSON在线工具 <p>
 * 版权所有：© www.sojson.com<p>
 * 博客地址：http://www.sojson.com/blog/  <p>
 * <p>
 * 
 * Redis Manager Utils
 * 
 * <p>
 * 
 * 区分　责任人　日期　　　　说明<br/>
 * 创建　周柏成　2016年6月2日 　<br/>
 *
 * @author zhou-baicheng
 * @email  so@sojson.com
 * @version 1.0,2016年6月2日 <br/>
 * 
 */
public class JedisManager {
    
    private JedisPool jedisPool;

    public Jedis getJedis() {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
        } catch (Exception e) {
            throw new JedisConnectionException(e);
        }
        return jedis;
    }

    public void returnResource(Jedis jedis, boolean isBroken) {
        if (jedis == null)
            return;
        jedis.close();
    }

    public byte[] getValueByKey(int dbIndex, byte[] key) throws Exception {
        Jedis jedis = null;
        byte[] result = null;
        boolean isBroken = false;
        try {
            jedis = getJedis();
            jedis.select(dbIndex);
            result = jedis.get(key);
        } catch (Exception e) {
            isBroken = true;
            throw e;
        } finally {
            returnResource(jedis, isBroken);
        }
        return result;
    }

    public void deleteByKey(int dbIndex, byte[] key) throws Exception {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = getJedis();
            jedis.select(dbIndex);
            Long result = jedis.del(key);
            LoggerUtils.fmtDebug(getClass(), "删除Session结果：%s" , result);
        } catch (Exception e) {
            isBroken = true;
            throw e;
        } finally {
            returnResource(jedis, isBroken);
        }
    }

    public void saveValueByKey(int dbIndex, byte[] key, byte[] value, int expireTime)
            throws Exception {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = getJedis();
            jedis.select(dbIndex);
            jedis.set(key, value);
            if (expireTime > 0)
                jedis.expire(key, expireTime);
        } catch (Exception e) {
            isBroken = true;
            throw e;
        } finally {
            returnResource(jedis, isBroken);
        }
    }

    public JedisPool getJedisPool() {
        return jedisPool;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

	/**
	 * 获取所有Session
	 * @param dbIndex
	 * @param redisShiroSession
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Collection<Session> AllSession(int dbIndex, String redisShiroSession) throws Exception {
		Jedis jedis = null;
        boolean isBroken = false;
        Set<Session> sessions = new HashSet<Session>();
		try {
            jedis = getJedis();
            jedis.select(dbIndex);
            
            Set<byte[]> byteKeys = jedis.keys((JedisShiroSessionRepository.REDIS_SHIRO_ALL).getBytes());  
            if (byteKeys != null && byteKeys.size() > 0) {  
                for (byte[] bs : byteKeys) {  
                	Session obj = SerializeUtil.deserialize(jedis.get(bs),  
                    		 Session.class);  
                     if(obj instanceof Session){
                    	 sessions.add(obj);  
                     }
                }  
            }  
        } catch (Exception e) {
            isBroken = true;
            throw e;
        } finally {
            returnResource(jedis, isBroken);
        }
        return sessions;
	}
	
	
	 /**
     * <p>通过key获取储存在redis中的value</p>
     * <p>并释放连接</p>
     * @param key
     * @return 成功返回value 失败返回null
     */
    @SuppressWarnings("deprecation")
    public String get(String key) {
        Jedis jedis = null;
        String value = null;
        try {
            jedis = jedisPool.getResource();
            value = jedis.get(key);
        } catch (Exception e) {
            jedisPool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
            returnResource(jedisPool, jedis);
        }
        return value;
    }

    /**
     * 
     * <pre>
     * 获取所有指定名称的key
     * </pre>
     *
     * @param keyStr
     * @return
     */
    public Set<String> getKeys(String keyStr){
        Jedis jedis = null;
        String value = null;
        try {
            jedis = getJedis();
            return jedis.keys(keyStr+"*");             
        } catch (Exception e) {           
            e.printStackTrace();
            return null;
        } finally {
            returnResource(jedisPool, jedis);
        }        
    }
    
    
    /**
     * <p>向redis存入key和value,并释放连接资源</p>
     * <p>如果key已经存在 则覆盖</p>
     * @param key
     * @param value
     * @return 成功 返回OK 失败返回 0
     */
    public String set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.set(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        } finally {
            returnResource(jedis, false);
        }
    }

    /**
     * <p>删除指定的key,也可以传入一个包含key的数组</p>
     * @param keys 一个key  也可以使 string 数组
     * @return 返回删除成功的个数 
     */
    public Long del(String... keys) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.del(keys);
        } catch (Exception e) {
            jedisPool.returnResourceObject(jedis);
            e.printStackTrace();
            return 0L;
        } finally {
            returnResource(jedisPool, jedis);
        }
    }

    /**
     * <p>通过key向指定的value值追加值</p>
     * @param key 
     * @param str 
     * @return 成功返回 添加后value的长度 失败 返回 添加的 value 的长度  异常返回0L
     */
    public Long append(String key, String str) {
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = jedisPool.getResource();
            res = jedis.append(key, str);
        } catch (Exception e) {
            jedisPool.returnResourceObject(jedis);
            e.printStackTrace();
            return 0L;
        } finally {
            returnResource(jedisPool, jedis);
        }
        return res;
    }

    /**
     * <p>判断key是否存在</p>
     * @param key
     * @return true OR false
     */
    public Boolean exists(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.exists(key);
        } catch (Exception e) {
            jedisPool.returnResourceObject(jedis);
            e.printStackTrace();
            return false;
        } finally {
            returnResource(jedisPool, jedis);
        }
    }
    
    /**
      * <p>设置key value,如果key已经存在则返回0,nx==> not exist</p>
      * @param key
      * @param value
      * @return 成功返回1 如果存在 和 发生异常 返回 0
      */

    public Long setnx(String key ,String value){
    Jedis jedis = null;
    try {
     jedis = jedisPool.getResource();
     return jedis.setnx(key, value);
    } catch (Exception e) {
        jedisPool.returnResourceObject(jedis);
        e.printStackTrace();
        return 0L;
    } finally {
        returnResource(jedisPool, jedis);
    }
    }
    
    /**
      * <p>设置key value并制定这个键值的有效期</p>
      * @param key
      * @param value
      * @param seconds 单位:秒
      * @return 成功返回OK 失败和异常返回null
      */
    public String set(String key,String value,int seconds){
    Jedis jedis = null;
    String res = null;
    try {
     jedis = jedisPool.getResource();
     res = jedis.setex(key, seconds, value);
    } catch (Exception e) {
        jedisPool.returnBrokenResource(jedis);
     e.printStackTrace();
    } finally {
     returnResource(jedisPool, jedis);
    }
    return res;
    }
    
    
    
    
    /**
      * <p>设置key的值,并返回一个旧值</p>
      * @param key
      * @param value
      * @return 旧值 如果key不存在 则返回null
      */

    public String getset(String key,String value){
    Jedis jedis = null;
    String res = null;
    try {
         jedis = jedisPool.getResource();
         res = jedis.getSet(key, value);
    } catch (Exception e) {
        jedisPool.returnResourceObject(jedis);
        e.printStackTrace();
    } finally {
        returnResource(jedisPool, jedis);
    }
        return res;
    }
    
  
    /**
     * 返还到连接池
     * 
     * @param pool
     * @param
     */
    public static void returnResource(JedisPool pool, Jedis jedis) {
        if (jedis != null) {
            //        pool.returnResource(jedis);
            pool.returnResourceObject(jedis);
        }
    }
	
}
