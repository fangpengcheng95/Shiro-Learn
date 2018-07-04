package com.fpc.Shiro.Controller;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.catalina.core.ApplicationContext;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fpc.Entity.Message;
import com.fpc.Entity.User;
import com.fpc.Entity.sendMessage;
import com.fpc.Service.IUserService;
import com.mysql.jdbc.Constants;

@Controller
@RequestMapping("/Shiro")
public class LoginController {
	
	@RequestMapping("/login")
	public String login( @RequestParam("username") String username , @RequestParam("password") String password ){
		Subject currentUser = SecurityUtils.getSubject();
		//验证当前用户是否已经登录，即是否登录
		if ( !currentUser.isAuthenticated() ){
			//把用户名和密码封装成UsernamePasswordToken
			UsernamePasswordToken token = new UsernamePasswordToken(username,password);
			token.setRememberMe(false);
			try{
				currentUser.login(token);
			} catch (UnknownAccountException ue) {
				// TODO: handle exception
				//用户名错误
				return "login";
			} catch (IncorrectCredentialsException ie) {
				// TODO: handle exception
				//密码错误
				return "login";
			} catch (LockedAccountException le) {
				// TODO: handle exception
				//账户被锁定
			}
			catch ( AuthenticationException aException ) {
				//所有异常的父类
				return "login";
			}
		}
		//登录成功
		return "redirect:/user.jsp";
	}
	
	@RequestMapping("/get_message")
	public ModelAndView get_message( @RequestParam("mobileNumber") String moblieNumber ){
		//取得电话号码，构造消息对象，然后通过短信服务器生成验证码发送到该手机上
		Message message = new Message();
		message.setMobileNumber(moblieNumber);
		message.setType((byte)1); //验证消息
		message.setContent("23456"); //消息的内容
	    message.setStatus((byte)1);//未发送状态
	    message.setCreateDate(new Date());
		//1.可以把待发送的消息存库，也可以不存库，现在先不存库
		
		//2.异步发送短信到redis队列
		//2.1先构造一个消息发送对象
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-redis.xml");
		sendMessage sendm = (sendMessage)context.getBean("sendMessage");
		sendm.sendMessages("sms_queue_web_online", message);
		System.out.println(message.getContent());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("code",message.getContent());
		modelAndView.setViewName("user");
		return modelAndView;
	}
	@RequestMapping("/logout")
	public String logout(){
		Subject currentuser = SecurityUtils.getSubject();
		currentuser.logout();
		return "login";
	}
}
