package com.yihao86.quartz;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.yihao86.dao.VideosDao;

@Component
@Configurable
@EnableScheduling
public class ScheduledTasks {
	
	@Autowired
	private VideosDao vdao;
	 //每个星期五上午十点执行
    public List<Map<String,Object>> reportCurrentByCron(){
        System.out.println ("现在的时间是："+new Date());
        return vdao.findBroadcast();
    }
    
    
}
