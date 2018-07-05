package com.fpc.UnitTest;

import org.apache.catalina.core.ApplicationContext;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.apple.eawt.Application;
import com.fpc.Service.IAccountService;
import com.fpc.ServiceImpl.AccountServiceImpl;

public class TransactionTest {
	@Test
	public void TestNoTransaction(){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		IAccountService accountService = (IAccountService) context.getBean("accountService");
		
		//Tom 向 Marry转账100元
		accountService.transfer("Tom", "Marry", 100);
	}
}
