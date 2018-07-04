package com.fpc.Entity;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
	private Integer messageId;
	private String mobileNumber;//电话号码
	private Byte type;//消息类型，1：登录验证码，2：订单信息
	
	private Date createDate;//消息创建的时间
	
	//消息处理时间
	private Date processTime;
	
	//消息状态：1：未发送，2：发送成功，3：发送失败
	private Byte status;
	
	private String content;//消息主体

	public Integer getMessageId() {
		return messageId;
	}

	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getProcessTime() {
		return processTime;
	}

	public void setProcessTime(Date processTime) {
		this.processTime = processTime;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
