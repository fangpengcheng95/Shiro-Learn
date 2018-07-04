package com.fpc.Entity;

import java.io.Serializable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;

//监听消息
@Component("smsMessageDelegateListener")
public class smsMessageDelegateListener {
	//监听Redis消息
	public void handleMessage( Serializable message ){
		Message mess = (Message) message;
		
		//发送短信
		//手机号: mess.getMobileNumber()
		//短信内容:mess.getContent();
		//send,发送状态sendStatus
		//如果发送不成功则直接return，离开该方法，或者继续重试
		//如果发送成功则需要，异步改写短信的状态；
		Jedis jedis = new Jedis("10.0.20.251");
		jedis.set("message", mess.getContent());
		Executor executor = Executors.newSingleThreadExecutor();
		executor.execute(new Runnable() {
			
			@Override
			public void run() {
				// TODO 自动生成的方法存根
				//读写短信数据表，将短信的发送状态改为已发送
			}
		});
		
	}
}
