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
		// System.out.println("��Ļ�ֱ���Ϊ:"+dm.widthPixels+" * "+dm.heightPixels);
		// System.out.println("״̬��:"+ getStatusBarHeight());
		// System.out.println("״̬��:"+ getStatusBarHeight());

		String str = "";
		DisplayMetrics dm = new DisplayMetrics(); //

		dm = this.getApplicationContext().getResources().getDisplayMetrics();
		int screenWidth = dm.widthPixels;
		int screenHeight = dm.heightPixels;
		float density = dm.density;
		float xdpi = dm.xdpi;
		float ydpi = dm.ydpi;
		str += "��Ļ�ֱ���Ϊ:" + dm.widthPixels + " * " + dm.heightPixels + "\n";
		str += "���Կ��:" + String.valueOf(screenWidth) + "pixels\n";
		str += "���Ը߶�:" + String.valueOf(screenHeight) + "pixels\n";
		str += "�߼��ܶ�:" + String.valueOf(density) + "\n";
		str += "X ά :" + String.valueOf(xdpi) + "����ÿӢ��\n";
		str += "Y ά :" + String.valueOf(ydpi) + "����ÿӢ��\n";
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
