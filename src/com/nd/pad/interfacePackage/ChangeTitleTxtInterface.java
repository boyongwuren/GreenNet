package com.nd.pad.interfacePackage;

import android.graphics.Bitmap;

import com.nd.pad.view.ViewPagerContent;


public interface ChangeTitleTxtInterface
{
	/**
	 *  �л�����
	 * @param title
	 */
      public void changeTitleTxt(String title,ViewPagerContent view);
      
      /**
       * �滻��ǰҳ��ı���ͼƬ
       * @param bmp
       */
      public void changeTitleWebPic(Bitmap bmp);
}
