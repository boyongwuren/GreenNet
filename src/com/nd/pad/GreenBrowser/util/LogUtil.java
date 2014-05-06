package com.nd.pad.GreenBrowser.util;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Toast;


/**
 * 工具类
 * @author zmp
 *
 */
public class LogUtil
{

	/**
	 * 显示服务器过来的信息
	 * @param context
	 * @param info
	 */
	public static void showServerBackInfo(String info)
	{
//		 AlertDialog alertDialog = new AlertDialog.Builder(SingleToolClass.content).
//					setMessage(info).
//					setTitle("服务端返回数据").
//					setPositiveButton("确定", new DialogInterface.OnClickListener()
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
	 * toast 提示
	 * @param text
	 */
	public static void showTip(String text)
	{
		Toast toast = Toast.makeText(SingleToolClass.content, text, Toast.LENGTH_SHORT);
		toast.show();
	}
}
