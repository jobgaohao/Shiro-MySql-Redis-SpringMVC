package com.sojson.seckill.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sojson.seckill.dto.Exposer;
import com.sojson.seckill.dto.SeckillExecution;
import com.sojson.seckill.exception.RepeatKillException;
import com.sojson.seckill.exception.SeckillCloseException;
import com.sojson.seckill.exception.SeckillException;
import com.sojson.seckill.mapper.SeckillMapper;
import com.sojson.seckill.model.Seckill;
import com.sojson.seckill.service.SeckillService;

@Service
public class SeckillServiceImpl implements SeckillService {

    private Logger logger=LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private SeckillMapper seckillMapper;
    
    @Override
    public List<Seckill> getSeckillList() {
        return seckillMapper.querySeckillList(0, 10);        
    }

    @Override
    public Seckill getSeckillById(Long seckillId) {
        //return seckillMapper.queryBySeckillId(seckillId);
        return null;
    }

    @Override
    public Exposer exportSeckillUrl(Long seckillId) {
        return null;
    }

    @Override
    public SeckillExecution executeSeckill(Long seckillId, Long userPhone, String md5)
                                                                                      throws SeckillException,
                                                                                      RepeatKillException,
                                                                                      SeckillCloseException {
        return null;
    }

}
