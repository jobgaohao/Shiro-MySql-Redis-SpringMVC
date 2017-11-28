package com.sojson.seckill.service.impl;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CyclicBarrier;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.sojson.core.shiro.cache.JedisManager;
import com.sojson.seckill.dto.Exposer;
import com.sojson.seckill.dto.SeckillExecution;
import com.sojson.seckill.dto.SeckillInfo;
import com.sojson.seckill.enums.EnumSeckillState;
import com.sojson.seckill.exception.RepeatKillException;
import com.sojson.seckill.exception.SeckillCloseException;
import com.sojson.seckill.exception.SeckillException;
import com.sojson.seckill.mapper.SeckillMapper;
import com.sojson.seckill.mapper.SuccessKilledMapper;
import com.sojson.seckill.model.Seckill;
import com.sojson.seckill.model.SuccessKilled;
import com.sojson.seckill.seckillOpportunity.AbstractBaseValidate;
import com.sojson.seckill.service.SeckillService;
import com.sojson.seckill.utils.Utils;

@Service
public class SeckillServiceImpl implements SeckillService {
 
    private final String PHONE_BEGIN="139";
    
    private Logger logger=LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private JedisManager jedisManager; 
    
    @Autowired
    public AbstractBaseValidate<SeckillInfo> abstractBaseValidate;
    
    //加入一个混淆字符串，避免用户猜出md5值
    private final String salt="zhaogang@123";
    
    @Autowired
    private SeckillMapper seckillMapper;
    
    @Autowired
    private SuccessKilledMapper successKilledMapper;
    
    @Override
    public List<Seckill> getSeckillList() {
        return seckillMapper.querySeckillList(0, 10);        
    }

    @Override
    public Seckill getSeckillById(Long seckillId) {
        return seckillMapper.queryBySeckillId(seckillId);       
    }

    @Override
    public Exposer exportSeckillUrl(Long seckillId) {
        Seckill seckill=seckillMapper.queryBySeckillId(seckillId);
        if(seckill==null){
            return new Exposer(false,seckillId);
        }
   
        Date startTime=seckill.getStartTime();
        Date endTime=seckill.getEndTime();
        Date nowTime=new Date();
        if(startTime.getTime()>nowTime.getTime()||endTime.getTime()<nowTime.getTime()){
            return new Exposer(false,seckillId,nowTime.getTime(),startTime.getTime(),endTime.getTime());
        }
        String md5=getMD5(seckillId);
        return new Exposer(true,md5,seckillId);
    }

    /**
     * 执行秒杀
     * 秒杀成功：减库存，增加明细
     * 秒杀失败：抛出异常，事务回滚
     * @param seckillId
     * @param userPhone
     * @param md5
     * @return
     * @throws Exception 
     * @see com.sojson.seckill.service.SeckillService#executeSeckill(java.lang.Long, java.lang.Long, java.lang.String)
     */
    @Override
    @Transactional
    public SeckillExecution executeSeckill(Long seckillId, Long userPhone, String md5)
              throws Exception {
        Date nowTime=new Date();
        if(StringUtils.isEmpty(md5)||!md5.equals(getMD5(seckillId))){
            throw new SeckillException("seckill data rewrite");//秒杀数据被重写
        }
        
                  
        SeckillInfo seckillInfo=new SeckillInfo();
        seckillInfo.setSeckill(getSeckillById(seckillId));
        seckillInfo.setUserPhone(userPhone);
        seckillInfo = (SeckillInfo)abstractBaseValidate.doValidate(seckillInfo);
        if(seckillInfo.isHasSeckillOpportunity()==false){
            throw new SeckillException("seckill fail,人品较差");
        }
        
        int insertCount=successKilledMapper.insertSuccessKilled(seckillId, userPhone);
        if(insertCount<=0){
            throw new RepeatKillException("seckill repeated");
        }
        else{
            int updateCount=seckillMapper.reduceNumber(seckillId, nowTime);
            if(updateCount<=0){
                throw new SeckillCloseException("seckill is closed");
            }
            else{
                SuccessKilled successKilled=successKilledMapper.queryByIdWithSeckill(seckillId, userPhone);
                return new SeckillExecution(seckillId, EnumSeckillState.SUCCESS,successKilled);
            }
        }
    }
    
    /**
     * 
     * <pre>
     * MD5加密混淆
     * </pre>
     *
     * @param seckillId
     * @return
     */
    public String getMD5(Long seckillId){
        String base=seckillId+"/"+salt;
        String md5=DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

    /**
     * 机器执行秒杀
     * @param seckillId
     * @param userPhone
     * @see com.sojson.seckill.service.SeckillService#addSeckillByMachine(java.lang.Long, java.lang.Long)
     */
    @Override
    @Transactional
    public void addSeckillByMachine(Long seckillId, Long userPhone) throws Exception
    {
        Date nowTime=new Date();                            
        SeckillInfo seckillInfo=new SeckillInfo();
        seckillInfo.setSeckill(getSeckillById(seckillId));
        seckillInfo.setUserPhone(userPhone);
        seckillInfo = (SeckillInfo)abstractBaseValidate.doValidate(seckillInfo);
        if(seckillInfo.isHasSeckillOpportunity()==false){
            throw new SeckillException("seckill fail,人品较差");
        }
        
        int updateCount=seckillMapper.reduceNumber(seckillId, nowTime);
        if(updateCount<=0){
           throw new SeckillCloseException("seckill is closed");
        }  
        else{
            int insertCount=successKilledMapper.insertSuccessKilled(seckillId, userPhone);
            if(insertCount<=0){
                throw new RepeatKillException("seckill repeated");
            }
        }     
        System.out.println(userPhone+"秒杀 success");
    }

    @Override
    public void startThreadSeckill(Integer threadCount, final Long seckillId) {
       for (int i = 0; i < threadCount; i++) {
          final int num=(i+1);
          new Thread(new Runnable(){
            @Override
            public void run() {
                Long userPhone=Utils.getUserPhone(PHONE_BEGIN);
                System.out.println("第"+num+"秒杀 "+userPhone+" begin");
                try {
                    addSeckillByMachine(seckillId,userPhone);
                } catch (Exception e) {
                    logger.error("", e);
                }
                System.out.println("第"+num+"秒杀 "+userPhone+" end");
            }              
          }).start();
       }
    }

    @Override
    public void startThreadSeckillOne(final Integer threadCount, final Long seckillId) throws Exception {       
        final Thread a=new Thread(new Runnable() {            
            @Override
            public void run() {
                for (int i = 0; i < threadCount; i++) {                   
                    jedisManager.set(Utils.getUserPhone(PHONE_BEGIN).toString(), seckillId.toString());                    
                }
            }
        });
                
        Thread b=new Thread(new Runnable() {           
            @Override
            public void run() {
                try {
                      a.join();//b线程在a线程执行完后进行
                      Set<String> phoneList=null;
                      do{
                          phoneList=jedisManager.getKeys(PHONE_BEGIN);
                          for (String phone : phoneList) {
                              addSeckillByMachine(seckillId,Long.parseLong(phone));
                              jedisManager.del(phone);
                          }
                          phoneList=jedisManager.getKeys(PHONE_BEGIN);
                      }while(phoneList.size()>0);
                  }
                catch (Exception e) {
                    logger.error("", e);
                }              
            }
        });
        
        a.start();
        b.start();
    }

    @Override
    public void startThreadSeckillTwo(Integer threadCount, final Long seckillId) throws Exception {
      int runner=threadCount;
      final CyclicBarrier cyclicBarrier=new CyclicBarrier(runner);
      final Random random=new Random();
      for (int i = 0; i < runner; i++) {
        final String phone=Utils.getUserPhone(PHONE_BEGIN).toString();
        new Thread(new Runnable() {            
            @Override
            public void run() {
                try {
                    long prepareTime=random.nextInt(10000)+100;
                    System.out.println(phone+"is preparing for time"+prepareTime);
                    Thread.sleep(prepareTime);
                    cyclicBarrier.await();//线程准备好后，等待他人      
                    System.out.println(phone+" starts running");
                    addSeckillByMachine(seckillId,Long.parseLong(phone));
                } catch (Exception e) {
                    e.printStackTrace();
                }                
            }
        }).start();
      }
    }

    /**
     * 运行定时任务
     * 每天下午2点到下午2:05期间的每1分钟触发 :"0 0-5 14 * * ?"    
     * 
     * @see com.sojson.seckill.service.SeckillService#testScheduled()
     */
    @Override
    @Scheduled(cron = "0/20 * * * * ? ")
    public void testScheduled() {
        DateFormat formatter= new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");       
        System.out.println("Spring task by annotation---->"+formatter.format(new Date()));
    }  
}
