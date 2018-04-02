package com.sojson.common.timer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sojson.permission.service.RoleService;


/**
 * 定时任务恢复数据
 *
 */
@Component
public class ToTimer{
	
	@Resource
	RoleService roleService;
	@Scheduled(cron = "0/20 * * * * ? ")
	public void run1() {
		DateFormat formatter= new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");		
		System.out.println("toTimer():-->"+formatter.format(new Date()));
	}

	
	
	
	
	
	
}
