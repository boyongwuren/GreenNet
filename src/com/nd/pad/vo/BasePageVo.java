package com.nd.pad.vo;

/**
 *  ����vo
 * @author zmp
 *
 */
public class BasePageVo
{

	public int id = 0;
	
	/**
	 *  ͼƬ��url��ַ
	 */
	public String picUrl = "";	

	
	/**
	 * ��Ϣ������
	 */
	public String infoString = "";
	
	/***
	 * ҳ���url��ַ
	 */
	public String webUrl = "http://m.taobao.com";
	
	@Override
	public String toString()
	{
		return "id = "+id+" picUrl= "+picUrl+" infoString = "+infoString+" webUrl= "+webUrl;
	}
}
