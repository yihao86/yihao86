package com.yihao86.quartz;

import java.util.Date;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Configurable
@EnableScheduling
public class ScheduledTasks {

	 //每个星期五上午十点执行
    public String reportCurrentByCron(){
        System.out.println ("现在的时间是："+new Date());
        String time =new Date().toString();
        return time;
    }
    
    
}
