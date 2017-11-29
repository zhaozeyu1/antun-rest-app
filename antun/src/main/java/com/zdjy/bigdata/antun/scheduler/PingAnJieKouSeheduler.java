package com.zdjy.bigdata.antun.scheduler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.zdjy.bigdata.antun.domain.User;
import com.zdjy.bigdata.antun.remote.PingAnJieKouSender;
import com.zdjy.bigdata.antun.service.UserService;

/**
 * 外发定时任务类
 * @author zdjy
 *
 */
@Component
public class PingAnJieKouSeheduler {
	private Logger logger=LoggerFactory.getLogger(PingAnJieKouSeheduler.class);
	@Autowired
	private UserService userService;
	@Autowired
	private PingAnJieKouSender pingAnJieKouSender;
	@Scheduled(cron="* * * * * ?")
	public void send(){
		logger.info("平安定时任务开始。。。。");
		//查询出所有未发送的数据（status=0）
		List<User> users=userService.findByStatus(0);
		logger.info("有"+users.size()+"条数据待发送");
		if(users.isEmpty())
			return;
		//循环发送
		for(User user:users){
			pingAnJieKouSender.send(user);
		}
		logger.info("平安定时任务结束。。。。");
	}
}