package com.nd.pad.GreenBrowser.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.PaintDrawable;
import android.view.WindowManager;

 
/**
 * 一些常用的全局属性
 * SharedPreferences
 * WindowManager
 * @author Administrator
 *
 */
public class SingleToolClass
{

	 public static SharedPreferences sharedPreferences;
	 
	 public static WindowManager windowManager;
	 
	 public static Context content;
	 
	 public static ImageDownloader imageDownloader;
	 
	 /**
	  * 返回屏幕的宽度
	 * @return
	 */
	public static int getScreenWidth()
	 {
		 return windowManager.getDefaultDisplay().getWidth();
	 }
	 
	/**
	 * 返回屏幕的高度
	 * @return
	 */
	 public static int getScreenHeight()
	 {
		 return windowManager.getDefaultDisplay().getHeight();
	 }
}
