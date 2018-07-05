package com.fpc.ServiceImpl;

import org.apache.shiro.authc.Account;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.fpc.Dao.AccountDao;
import com.fpc.Service.IAccountService;
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
public class AccountServiceImpl implements IAccountService{
	private AccountDao accounDao;
	
	public void setAccounDao(AccountDao accounDao) {
		this.accounDao = accounDao;
	}
	
	
	@Override
	public void transfer(String outer, String inner, int money) {
				accounDao.out(outer, money);
				int i = 1/0;
				accounDao.in(inner, money);
			}
	}
