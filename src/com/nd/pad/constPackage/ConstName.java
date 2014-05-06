package com.nd.pad.constPackage;

import com.nd.pad.GreenBrowser.R.string;

public class ConstName {

	public static final String[] VIEW_PAGER_TITLE = {"首页","导航","收藏"};

	/**
	 * 默认的重点推荐的推荐内容
	 * 在没有网络的情况下，使用
	 */
	public static final String DEFAUL_KEY_CALL = "{" +
			"'info':" +
			"[   {'id':01,'picUrl':'http://www.baidu.com/icon','infoTxt':'百度','webUrl':'http://m.baidu.com'}," +
			"{'id':02,'picUrl':'http://www.baidu.com/icon','infoTxt':'网易','webUrl':'http://m.baidu.com'},  " +
			"{'id':03,'picUrl':'http://www.baidu.com/icon','infoTxt':'美团','webUrl':'http://m.baidu.com'},  " +
			"{'id':04,'picUrl':'http://www.baidu.com/icon','infoTxt':'新浪','webUrl':'http://m.baidu.com'},   " +
			"{'id':05,'picUrl':'http://www.baidu.com/icon','infoTxt':'搜狐','webUrl':'http://m.baidu.com'}, " +
			"{'id':06,'picUrl':'http://www.baidu.com/icon','infoTxt':'谷歌','webUrl':'http://m.baidu.com'},  " +
			"{'id':07,'picUrl':'http://www.baidu.com/icon','infoTxt':'宜搜','webUrl':'http://m.baidu.com'}, " +
			"{'id':08,'picUrl':'http://www.baidu.com/icon','infoTxt':'天猫','webUrl':'http://m.baidu.com'}" +
			"]" +
			"} ";
	
	
	/***
	 * SharedPreference 的名字
	 */
	public static final String SHARED_PREFERENCE = "GreenNetSharedPreference";
	
	/**
	 * 标题的宽度
	 */
	public static final int TITLE_WIDTH = 206;
	
	/**
	 * 标签的总个数
	 */
	public static final int TITLE_BAR_TOTAL_NUM = 10;
	
	/**
	 * webview 图片的保存路径
	 */
	public static final String WEB_ICON_PATH = "webIcon";
}
