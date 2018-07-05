package com.fpc.ServiceImpl;

import org.apache.shiro.authc.Account;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.fpc.Dao.AccountDao;
import com.fpc.Service.IAccountService;

public class AccountServiceImpl implements IAccountService{
	private AccountDao accounDao;
	private TransactionTemplate transactionTemplate;
	
	public void setAccounDao(AccountDao accounDao) {
		this.accounDao = accounDao;
	}
	
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	
	@Override
	public void transfer(String outer, String inner, int money) {
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				// TODO 自动生成的方法存根
				accounDao.out(outer, money);
				int i = 1/0;
				accounDao.in(inner, money);
			}
		});
	}

}
