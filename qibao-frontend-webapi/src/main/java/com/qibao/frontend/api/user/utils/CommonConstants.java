/************************************************************************************
 *  Copyright 2012 WZITech Corporation. All rights reserved.
 *	
 *	模	块：		RuntimeConstants
 *	包	名：		com.wzitech.chaos.framework.common
 *	项目名称：	chaos-common 
 *	作	者：		Shawn
 *	创建时间：	2012-4-13
 *	描	述：		系统运行时常量定义类
 *	更新纪录：	1. Shawn 创建于 2012-4-13 下午4:15:17
 * 				
 ************************************************************************************/
package com.qibao.frontend.api.user.utils;

/**
 * 系统运行时常量定义类
 * @author Shawn
 *
 */
public class CommonConstants {

	/**
	 * 用户自定义session
	 */
	public static final String TOKEN_NAME = "_TV2";

	/**
	 * 用户ID-请求认证HTTP HEAD标签
	 */
	public static final String SERVICE_REQUEST_HEADER_UID = "uid";

	/**
	 * 用户authkey-请求认证HTTP HEAD标签
	 */
	public static final String SERVICE_REQUEST_HEADER_AUTHKEY = "authkey";

	/**
	 * ip自定义SessionId
	 */
	public static final String PARAMS_SESSIONID = "_SRHSI";

	/**
	 * 浏览器session
	 */
	public static final String ONLY_ONE = "JSESSIONID";


}
