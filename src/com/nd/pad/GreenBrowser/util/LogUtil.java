package com.nd.pad.GreenBrowser.util;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Toast;


/**
 * ������
 * @author zmp
 *
 */
public class LogUtil
{

	/**
	 * ��ʾ��������������Ϣ
	 * @param context
	 * @param info
	 */
	public static void showServerBackInfo(String info)
	{
//		 AlertDialog alertDialog = new AlertDialog.Builder(SingleToolClass.content).
//					setMessage(info).
//					setTitle("����˷�������").
//					setPositiveButton("ȷ��", new DialogInterface.OnClickListener()
//					{
//						@Override
//						public void onClick(DialogInterface dialog, int which)
//						{
//							dialog.cancel();
//						}
//					}).create();
//			alertDialog.show();
	}
	
	/**
	 * toast ��ʾ
	 * @param text
	 */
	public static void showTip(String text)
	{
		Toast toast = Toast.makeText(SingleToolClass.content, text, Toast.LENGTH_SHORT);
		toast.show();
	}
}
