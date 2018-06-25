package com.fpc.Shiro.Controller;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fpc.Entity.User;
import com.fpc.Service.IUserService;

@Controller
@RequestMapping("/Shiro")
public class LoginController {
	
	@Resource
	private IUserService userService;
	
	@RequestMapping("/login")
	public String login( @RequestParam("username") String username , @RequestParam("password") String password ){
		Subject currentUser = SecurityUtils.getSubject();
		//验证当前用户是否已经登录，即是否登录
		if ( !currentUser.isAuthenticated() ){
			//把用户名和密码封装成UsernamePasswordToken
			UsernamePasswordToken token = new UsernamePasswordToken(username,password);
			token.setRememberMe(true);
			User user = userService.getUserByName(username);
			System.out.println(user.getPassword());
			try{
				currentUser.login(token);
			} catch (UnknownAccountException ue) {
				// TODO: handle exception
				//用户名错误
				//return "login";
			} catch (IncorrectCredentialsException ie) {
				// TODO: handle exception
				//密码错误
			} catch (LockedAccountException le) {
				// TODO: handle exception
				//账户被锁定
			}
			catch ( AuthenticationException aException ) {
				//所有异常的父类
			}
			
		}
		//登录成功
		return "redirect:/user.jsp";
	}
}
