package com.nd.pad.GreenBrowser.util;

import java.io.File;

/**
 * 文件相关工具类
 * @author Administrator
 *
 */
public class FileUtils 
{
	/**
	 * 创建一个文件夹
	 * 如果文件夹不存在 
	 * @param path 文件夹路径
	 * @return
	 */
	public static String getDirs(String path)
	{
		 File dir = new File(path);
		 if (!dir.exists()) 
		 {
			 dir.mkdirs();
		 }
		 return path;
	}
 
}
