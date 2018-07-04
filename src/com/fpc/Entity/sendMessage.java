package com.fpc.Entity;

import java.io.Serializable;

import org.springframework.data.redis.core.RedisTemplate;

//定义一个消息发送对象
public class sendMessage {
	private RedisTemplate<String, Object> redisTemplate;

	public RedisTemplate<String, Object> getRedisTemplate() {
		return redisTemplate;
	}

	public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	
	public void sendMessages( String channel,Serializable message ){
		redisTemplate.convertAndSend(channel, message);
	}
}
