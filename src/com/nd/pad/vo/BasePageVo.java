package com.nd.pad.vo;

/**
 *  基础vo
 * @author zmp
 *
 */
public class BasePageVo
{

	public int id = 0;
	
	/**
	 *  图片的url地址
	 */
	public String picUrl = "";	

	
	/**
	 * 信息的文字
	 */
	public String infoString = "";
	
	/***
	 * 页面的url地址
	 */
	public String webUrl = "http://m.taobao.com";
	
	@Override
	public String toString()
	{
		return "id = "+id+" picUrl= "+picUrl+" infoString = "+infoString+" webUrl= "+webUrl;
	}
}
