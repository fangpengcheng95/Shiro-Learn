package com.fpc.ServiceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fpc.Dao.UserDao;
import com.fpc.Entity.User;
import com.fpc.Service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {
	@Resource
	private UserDao userDao;

	@Override
	public User getUserByName(String username) {
		// TODO 自动生成的方法存根
		return userDao.selectByName(username);
	}
	
}
