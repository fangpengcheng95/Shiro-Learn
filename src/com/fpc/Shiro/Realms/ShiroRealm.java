package com.fpc.Shiro.Realms;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.fpc.Entity.User;
import com.fpc.Service.IUserService;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class ShiroRealm extends AuthorizingRealm {
	
	@Resource
	private IUserService userService;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO 自动生成的方法存根
		String username = (String)token.getPrincipal(); //得到用户名
		String password = new String((char[])token.getCredentials()); //得到密码
		User user = userService.getUserByName(username);
		
		//检查是否有此用户
		if ( user == null ) {
			throw new UnknownAccountException();
		}
		/*
		 * AuthenticationRealm使用CredentialsMatcher进行密码匹配
		 * */
		if ( username != null && password != null ) {
			return new SimpleAuthenticationInfo(username,user.getPassword(),null,getName());
		} else {
			return null;
		}
	}
	
}
