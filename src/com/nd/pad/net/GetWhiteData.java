package com.nd.pad.net;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.nd.pad.constPackage.URLConst;
import com.nd.pad.interfacePackage.LoadApiBackInterface;
import com.nd.pad.interfacePackage.LoadWhiteBackInterface;
import com.nd.pad.GreenBrowser.util.LoadServerAPIHelp;

/**
 * 获取白名单
 * 
 * @author zmp
 * 
 */
public class GetWhiteData
{
	private static LoadWhiteBackInterface loadWhiteBackInterface;
	
	public static void getWhiteData(LoadWhiteBackInterface loadWhiteBackInterface)
	{
		GetWhiteData.loadWhiteBackInterface = loadWhiteBackInterface;
		LoadServerAPIHelp.loadServerApi(URLConst.WHITENET_URL, new LoadWhiteDataBack(), URLConst.WHITENET_PARAM);
	}

	private static class LoadWhiteDataBack implements LoadApiBackInterface
	{

		@Override
		public void loadApiBackString(String jsonString)
		{
			ArrayList<String> whitenetList = new ArrayList<String>();
			try {
				JSONObject jsonObject = new JSONObject(jsonString).getJSONObject("data");
				JSONArray jsonArray = (JSONArray) jsonObject.getJSONArray("datalist");
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject2 = (JSONObject) jsonArray.opt(i);
					String address = jsonObject2.getString("address");
					whitenetList.add(address);
				}
			} catch (Exception e) {
				 
			}
         
			loadWhiteBackInterface.loadWhiteBack(whitenetList);
		}

	}

}
