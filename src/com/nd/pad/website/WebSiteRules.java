package com.nd.pad.website;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


// HTTP URL∏Ò Ω£∫http://host[:port][abs_path][;url-params][?query-string][#anchor]
//1.http://www.mywebsite.com/sj/test;id=8079?name=sviergn&x=true#stuff  
//2.www.guet.edu.cn  
//3.www.guet.edu.cn:80

public class WebSiteRules 
{	
	private String url;
	private String urlRules;
	public WebSiteRules()
	{
		url = "(ftp://|http://|https://)?([\\w-]+\\.)+";
	}
	
	void SetRules(String rules)
	{
		urlRules = "";
		if(rules == null || rules.length() <= 0)
		{
			return ;
		}
		
		urlRules = url + rules + "*";		
	}
	
	boolean MatchRules(String urlAddress)
	{	
		if(urlAddress == null || urlAddress.length() <= 0)
		{
			return false;
		}
		
		Pattern pattern = Pattern.compile(urlRules);
		Matcher matcher = pattern.matcher(urlAddress);

		return matcher.find();	
	}
}
