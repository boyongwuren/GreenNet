package com.nd.pad.GreenBrowser.util;

import java.io.File;

/**
 * �ļ���ع�����
 * @author Administrator
 *
 */
public class FileUtils 
{
	/**
	 * ����һ���ļ���
	 * ����ļ��в����� 
	 * @param path �ļ���·��
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
