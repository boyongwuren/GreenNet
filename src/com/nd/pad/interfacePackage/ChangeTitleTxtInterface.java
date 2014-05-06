package com.nd.pad.interfacePackage;

import android.graphics.Bitmap;

import com.nd.pad.view.ViewPagerContent;


public interface ChangeTitleTxtInterface
{
	/**
	 *  切换标题
	 * @param title
	 */
      public void changeTitleTxt(String title,ViewPagerContent view);
      
      /**
       * 替换当前页面的标题图片
       * @param bmp
       */
      public void changeTitleWebPic(Bitmap bmp);
}
