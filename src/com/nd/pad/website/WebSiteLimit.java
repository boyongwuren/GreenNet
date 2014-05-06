package com.nd.pad.website;


import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebSiteLimit 
{
	//白单列表
	private ArrayList<String> whiteList = null;
	
	//规则对像
	private WebSiteRules urlRules = null;
	
	public WebSiteLimit()
	{
		if(whiteList == null)
		{
			whiteList = new ArrayList<String>();
		}
		
		if(urlRules == null)
		{
			urlRules = new WebSiteRules();
		}
	}
	
	public void ClearWhiteList()
	{
		if(whiteList != null)
		{
			whiteList.clear();
		}
	}
	/*
	 * 增加白名单
	 */
	public void AddWhiteNet(String whitenet)
	{
		whiteList.add(whitenet);
	}

	public void AddWhiteNet(ArrayList<String> _data)
	{
		for (int i = 0; i < _data.size(); i++) 
		{			
			AddWhiteNet(_data.get(i));
		}		
	}
	
	public void AddWhiteNet(String [] _data)
	{
		for (int i=0; i<_data.length; i++)
		{
			AddWhiteNet(_data[i]);
		}
	}
	
	/**
	 * 是否符合规则
	 * netstr 是要测试的网站
	 * 是返回true，否则返回false
	 */
	public boolean MatchWhiteNet(String netstr)
	{		
		for (int i = 0; i < whiteList.size(); i++) 
		{
			System.out.println("white list have:"+whiteList.get(i));
			urlRules.SetRules(whiteList.get(i));			
			boolean res = urlRules.MatchRules(netstr);
			if(res)
			{	
				return true;
			}			
		}		
		return false;
	}
	
	public String GetUrl(String url)
	{		
		if(!MatchWhiteNet(url))
	    {
	    	url = "file:///android_asset/error.html"; 	
	    }
		System.out.println(url);
		return url;
	}
}
