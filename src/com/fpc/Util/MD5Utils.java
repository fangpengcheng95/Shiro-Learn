package com.fpc.Util;

import org.apache.shiro.crypto.hash.SimpleHash;

public class MD5Utils {
	/*MD5加密,Shiro框架中自带MD5加密，没有MD5解密*/
	
	/*该工具用于生成密码*/
	/*
	 * 1.先用MD5进行加密，salt使用username，加密1024次
	 * 2.再用SHA1进行加密，salt使用password，加密1024次
	 * */
	public static void main( String[] args ){
		String username = "fpc";
		String password = "123456";
		Object pass = new SimpleHash("MD5", password,null,1024);
		System.out.println(pass);
//		Object pass1 = new SimpleHash("SHA1", pass,password,1024);
//		System.out.println(pass1);
	}
}
