package com.nd.pad.GreenBrowser;

import java.io.File;
import java.lang.reflect.Field;

import com.nd.pad.constPackage.ConstName;
import com.nd.pad.GreenBrowser.util.FileUtils;
import com.nd.pad.GreenBrowser.util.ImageDownloader;
import com.nd.pad.GreenBrowser.util.SingleToolClass;

import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.webkit.WebIconDatabase;

public class GreenNetApplication extends Application
{

	public GreenNetApplication()
	{

	}

	@Override
	public void onCreate()
	{
		super.onCreate();

		SingleToolClass.content = this;
		SingleToolClass.imageDownloader = new ImageDownloader(this);
		SingleToolClass.sharedPreferences = getSharedPreferences(ConstName.SHARED_PREFERENCE, 0);
		SingleToolClass.windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);

		// DisplayMetrics dm = new DisplayMetrics();
		// SingleToolClass.windowManager.getDefaultDisplay().getMetrics(dm);
		// System.out.println("ÆÁÄ»·Ö±æÂÊÎª:"+dm.widthPixels+" * "+dm.heightPixels);
		// System.out.println("×´Ì¬À¸:"+ getStatusBarHeight());
		// System.out.println("×´Ì¬À¸:"+ getStatusBarHeight());

		String str = "";
		DisplayMetrics dm = new DisplayMetrics(); //

		dm = this.getApplicationContext().getResources().getDisplayMetrics();
		int screenWidth = dm.widthPixels;
		int screenHeight = dm.heightPixels;
		float density = dm.density;
		float xdpi = dm.xdpi;
		float ydpi = dm.ydpi;
		str += "ÆÁÄ»·Ö±æÂÊÎª:" + dm.widthPixels + " * " + dm.heightPixels + "\n";
		str += "¾ø¶Ô¿í¶È:" + String.valueOf(screenWidth) + "pixels\n";
		str += "¾ø¶Ô¸ß¶È:" + String.valueOf(screenHeight) + "pixels\n";
		str += "Âß¼­ÃÜ¶È:" + String.valueOf(density) + "\n";
		str += "X Î¬ :" + String.valueOf(xdpi) + "ÏñËØÃ¿Ó¢³ß\n";
		str += "Y Î¬ :" + String.valueOf(ydpi) + "ÏñËØÃ¿Ó¢³ß\n";
		System.out.println(str);
		
		String webIconPath = getCacheDir().getAbsolutePath()+File.separator+ConstName.WEB_ICON_PATH;
		FileUtils.getDirs(webIconPath);
		WebIconDatabase.getInstance().open(webIconPath);
	}

	public int getStatusBarHeight()
	{
		Class<?> c = null;
		Object obj = null;
		Field field = null;
		int x = 0, statusBarHeight = 0;
		try {
			c = Class.forName("com.android.internal.R$dimen");
			obj = c.newInstance();
			field = c.getField("status_bar_height");
			x = Integer.parseInt(field.get(obj).toString());
			statusBarHeight = SingleToolClass.content.getResources().getDimensionPixelSize(x);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		return statusBarHeight;
	}

}
