package com.sojson.seckill.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.sojson.seckill.service.SeckillScheduledService;

public class SeckillScheduledServiceImpl implements SeckillScheduledService {

    @Override
    public void testScheduledByXML() {
        DateFormat formatter= new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");       
        System.out.println("Spring task by xml---->"+formatter.format(new Date()));
    }

}
