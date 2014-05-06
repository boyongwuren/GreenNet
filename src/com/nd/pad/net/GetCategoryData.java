package com.nd.pad.net;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.nd.pad.constPackage.URLConst;
import com.nd.pad.interfacePackage.LoadApiBackInterface;
import com.nd.pad.view.guideOnlie.GuideOnLineView.LoadCategoryBackInterface;
import com.nd.pad.vo.CategoryVo;
import com.nd.pad.GreenBrowser.util.LoadServerAPIHelp;

/**
 * 加载导航分类
 * 
 * @author zmp
 * 
 */
public class GetCategoryData
{
    private static LoadCategoryBackInterface loadCategoryBackInterface;
	public static void getCategoryData(LoadCategoryBackInterface loadCategoryBackInterface)
	{
		GetCategoryData.loadCategoryBackInterface = loadCategoryBackInterface;
		LoadServerAPIHelp.loadServerApi(URLConst.CATEGORY_URL, new LoadCategoryBack(), URLConst.CATEGORY_PARAM);
	}

	private static class LoadCategoryBack implements LoadApiBackInterface
	{
		@Override
		public void loadApiBackString(String jsonString)
		{
			ArrayList<CategoryVo> websiteList = new ArrayList<CategoryVo>();
			try {
				JSONObject jsonObject = new JSONObject(jsonString).getJSONObject("data");
				JSONArray jsonArray = (JSONArray) jsonObject.getJSONArray("datalist");
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject2 = (JSONObject) jsonArray.opt(i);
					CategoryVo vo = new CategoryVo();
					vo.id = jsonObject2.getInt("id");
					vo.parentId = jsonObject2.getInt("parentid");
					vo.iconUrl = jsonObject2.getString("icon");
					vo.name = jsonObject2.getString("name");
					vo.description = jsonObject2.getString("description");
					vo.order = jsonObject2.getInt("ordering");
					websiteList.add(vo);
				}
			} catch (Exception e) 
			{
				 
			}
			
			loadCategoryBackInterface.loadCategoryBack(websiteList);

		}

	}

}
