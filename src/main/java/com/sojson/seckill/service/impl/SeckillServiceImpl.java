package com.sojson.seckill.service.impl;

import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import com.sojson.seckill.dto.Exposer;
import com.sojson.seckill.dto.SeckillExecution;
import com.sojson.seckill.enums.EnumSeckillState;
import com.sojson.seckill.exception.RepeatKillException;
import com.sojson.seckill.exception.SeckillCloseException;
import com.sojson.seckill.exception.SeckillException;
import com.sojson.seckill.mapper.SeckillMapper;
import com.sojson.seckill.mapper.SuccessKilledMapper;
import com.sojson.seckill.model.Seckill;
import com.sojson.seckill.model.SuccessKilled;
import com.sojson.seckill.service.SeckillService;

@Service
public class SeckillServiceImpl implements SeckillService {

    private Logger logger=LoggerFactory.getLogger(this.getClass());
    
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
     * @throws SeckillException
     * @throws RepeatKillException
     * @throws SeckillCloseException
     * @see com.sojson.seckill.service.SeckillService#executeSeckill(java.lang.Long, java.lang.Long, java.lang.String)
     */
    @Override
    @Transactional
    public SeckillExecution executeSeckill(Long seckillId, Long userPhone, String md5)
              throws SeckillException,
              RepeatKillException,
              SeckillCloseException {
        Date nowTime=new Date();
        if(StringUtils.isEmpty(md5)||!md5.equals(getMD5(seckillId))){
            throw new SeckillException("seckill data rewrite");//秒杀数据被重写
        }
        
        try {
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
        }catch (SeckillCloseException e1)
        {
            throw e1;
        }catch (RepeatKillException e2)
        {
            throw e2;
        }catch (Exception e)
        {
            logger.error(e.getMessage(),e);
            //所以编译期异常转化为运行期异常
            throw new SeckillException("seckill inner error :"+e.getMessage());
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

}
