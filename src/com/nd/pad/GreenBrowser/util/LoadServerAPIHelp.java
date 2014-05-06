package com.nd.pad.GreenBrowser.util;

import com.nd.pad.constPackage.HandlerConst;
import com.nd.pad.interfacePackage.LoadApiBackInterface;
import com.nd.pad.net.LoadServerThread;

import android.os.Handler;
import android.os.Message;

/**
 * �����࣬�������ط���˽ӿڵ�ͳһ���
 * @author zmp
 *
 */
public class LoadServerAPIHelp 
{
 
	/**
	 * �����������
	 * ���������̼߳��ط���˶�Ӧ�Ľӿ�
	 * @param serverApiUrl
	 * @param loadApiBackInterface ������ɵĻص�
	 */
	public static void loadServerApi(String serverApiUrl,LoadApiBackInterface loadApiBackInterface,String paramString)
	{
		new LoadServerThread(serverApiUrl, new LoadDataHandler(loadApiBackInterface),paramString).start();
	}
	
	
	/**
	 * ������ط���˽ӿ���ɵĲ���
	 * @author zmp
	 *
	 */
	private static class LoadDataHandler extends Handler
	{
		private LoadApiBackInterface loadApiBackInterface;
		
		
		public LoadDataHandler(LoadApiBackInterface loadApiBackInterface)
		{
			this.loadApiBackInterface = loadApiBackInterface;
		}
		
		@Override
		public void handleMessage(Message msg)
		{
			switch (msg.what) 
			{
				case HandlerConst.LOAD_OK:
					if(loadApiBackInterface != null)
					{
						loadApiBackInterface.loadApiBackString(msg.getData().getString(HandlerConst.LOAD_FILE_KEY));
					}
					break;
					
				case HandlerConst.LOAD_FALL:
					break;
					
				default:
					break;
			}
			
			LogUtil.showServerBackInfo(msg.getData().getString(HandlerConst.LOAD_FILE_KEY));
		}
	}

}
