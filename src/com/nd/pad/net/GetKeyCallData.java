package com.nd.pad.net;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nd.pad.constPackage.URLConst;
import com.nd.pad.interfacePackage.LoadApiBackInterface;
import com.nd.pad.interfacePackage.LoadKeyCallBackInterface;
import com.nd.pad.vo.KeyCallItemVo;
import com.nd.pad.GreenBrowser.util.LoadServerAPIHelp;

/**
 * 获取重点推荐数据
 * 
 * @author zmp
 * 
 */
public class GetKeyCallData
{
	private static LoadKeyCallBackInterface loadKeyCallBackInterface;
	public static void getKeyCallData(LoadKeyCallBackInterface loadKeyCallBackInterface)
	{
		GetKeyCallData.loadKeyCallBackInterface = loadKeyCallBackInterface;
		LoadServerAPIHelp.loadServerApi(URLConst.KEY_CALL_URL, new LoadKeyCallBack(), URLConst.KEY_CALL_PARAM);
	}

	private static class LoadKeyCallBack implements LoadApiBackInterface
	{

		@Override
		public void loadApiBackString(String jsonString)
		{
			ArrayList<KeyCallItemVo> websiteList = new ArrayList<KeyCallItemVo>();

			JSONObject jsonObject;
			try {
				jsonObject = new JSONObject(jsonString).getJSONObject("data");
				JSONArray jsonArray = (JSONArray) jsonObject.getJSONArray("datalist");
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject2 = (JSONObject) jsonArray.opt(i);
					KeyCallItemVo vo = new KeyCallItemVo();
					vo.picUrl = jsonObject2.getString("iconurl");
					vo.infoString = jsonObject2.getString("name");
					vo.webUrl = jsonObject2.getString("address");
					websiteList.add(vo);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			loadKeyCallBackInterface.loadKeyCallBack(websiteList);

		}
		
		 
	}

}
