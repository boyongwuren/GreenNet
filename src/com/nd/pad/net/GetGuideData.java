package com.nd.pad.net;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.nd.pad.constPackage.URLConst;
import com.nd.pad.interfacePackage.LoadApiBackInterface;
import com.nd.pad.view.guideOnlie.GuideOnlineLittle.LoadGuideDataBackInterface;
import com.nd.pad.vo.GuideOnlineItemVo;
import com.nd.pad.GreenBrowser.util.LoadServerAPIHelp;

/**
 * 获取导航分类下面的 数据
 * 
 * @author zmp
 * 
 */
public class GetGuideData
{

	private static LoadGuideDataBackInterface loadGuideDataBackInterface;
	
	public static void getGuideData(int categoryId,LoadGuideDataBackInterface loadGuideDataBackInterface)
	{
		GetGuideData.loadGuideDataBackInterface = loadGuideDataBackInterface;
		String paramString = URLConst.NAVIGATION_PARAM + categoryId + "}";
		LoadServerAPIHelp.loadServerApi(URLConst.NAVIGATION_URL, new LoadGuidBack(), paramString);
	}

	private static class LoadGuidBack implements LoadApiBackInterface
	{
		@Override
		public void loadApiBackString(String result)
		{
			ArrayList<GuideOnlineItemVo> websiteList = new ArrayList<GuideOnlineItemVo>();

			try {

				JSONObject jsonObject = new JSONObject(result).getJSONObject("data");
				JSONArray jsonArray = (JSONArray) jsonObject.getJSONArray("datalist");
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject2 = (JSONObject) jsonArray.opt(i);
					GuideOnlineItemVo vo = new GuideOnlineItemVo();
					vo.id = jsonObject2.getInt("id");
					vo.categoryId = jsonObject2.getInt("categoryid");
					vo.webUrl = jsonObject2.getString("address");
					vo.picUrl = jsonObject2.getString("iconurl");
					vo.infoString = jsonObject2.getString("name");
					vo.ordering = jsonObject2.getInt("ordering");
					websiteList.add(vo);
				}

			} catch (Exception e) {
				// TODO: handle exception
			}
			
			loadGuideDataBackInterface.loadGuideDataBack(websiteList);
			
		}

	}
	
	
	
	
	

}
