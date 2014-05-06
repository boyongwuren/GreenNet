package com.nd.pad.constPackage;

/**
 * url地址的常量
 * 
 * @author Administrator
 * 
 */
public class URLConst
{

	/***
	 * 重点推荐的服务端地址
	 */
	public static final String KEY_CALL_URL = "http://openapi2.stu.test.nd/handler/taskhandler.ashx";
	public static final String KEY_CALL_PARAM = "{\"op\":\"net.getrecommendlist\"}";

	/**
	 * 请求白名单
	 */
	public static String WHITENET_URL = "http://openapi2.stu.test.nd/handler/taskhandler.ashx";
	public static String WHITENET_PARAM = "{\"op\":\"net.getwhitelist\"}";

	/**
	 *  请求类别
	 */
	public static final String CATEGORY_URL = "http://openapi2.stu.test.nd/handler/nethandler.ashx";
	public static final String CATEGORY_PARAM = "{\"op\":\"net.getcategorylist\"}";
	
	/**
	 * 请滶类别下的导航
	 */
	public static final String NAVIGATION_URL = "http://openapi2.stu.test.nd/handler/nethandler.ashx";
	public static final String NAVIGATION_PARAM = "{\"op\":\"net.getnavigationlist\",\"categoryid\":";

}
