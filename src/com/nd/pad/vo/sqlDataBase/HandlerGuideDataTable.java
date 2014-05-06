package com.nd.pad.vo.sqlDataBase;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nd.pad.constPackage.database.GuideDataTableConst;
import com.nd.pad.vo.GuideOnlineItemVo;

/**
 * 操作 导航页面 分类下的 数据
 * 
 * @author zmp
 * 
 */
public class HandlerGuideDataTable
{
	/**
	 * 插入导航页面 分类下的 数据
	 * 
	 * @param infoTxt
	 * @param picUrl
	 * @param webUrl
	 */
	public static void insertDataToTable(int id,int categoryId, String infoTxt, String picUrl, String webUrl, int order)
	{
		SQLiteDatabase sqLiteDatabase = GreenNetOpenHelper.getInstance().getReadableDatabase();

		Cursor cursor = sqLiteDatabase.query(GuideDataTableConst.TABLE_NAME, new String[] { GuideDataTableConst.CATEGORY_ID }, GuideDataTableConst.CATEGORY_ID + "=? and " + GuideDataTableConst.WEB_URL + "=?", new String[] { categoryId + "", webUrl }, null, null, null);

		if (cursor.moveToNext()) {
			// 有这条记录
		} else {
			// 没有这条记录
			// 插入
			ContentValues cv = new ContentValues();
			cv.put(GuideDataTableConst.TABLE_ID, id);
			cv.put(GuideDataTableConst.CATEGORY_ID, categoryId);
			cv.put(GuideDataTableConst.INFO_TXT, infoTxt);
			cv.put(GuideDataTableConst.PIC_URL, picUrl);
			cv.put(GuideDataTableConst.WEB_URL, webUrl);
			cv.put(GuideDataTableConst.ORDERING, order);

			sqLiteDatabase.insert(GuideDataTableConst.TABLE_NAME, null, cv);
		}

		sqLiteDatabase.close();
	}
	
	public static void insertDataToTable(ArrayList<GuideOnlineItemVo> gVos)
	{
		for (int i = 0; i < gVos.size(); i++) 
		{
			insertDataToTable(gVos.get(i));
		}
	}

	public static void insertDataToTable(GuideOnlineItemVo gVo)
	{
		insertDataToTable(gVo.id,gVo.categoryId, gVo.infoString, gVo.picUrl, gVo.webUrl, gVo.ordering);
	}
	
	private static HashMap<String, ArrayList<GuideOnlineItemVo>> dataMap = new HashMap<String, ArrayList<GuideOnlineItemVo>>();

	/**
	 * 获取经常访问的数据
	 * 
	 * @return
	 */
	public static ArrayList<GuideOnlineItemVo> getDataFromTable(int categoryId)
	{
		
		if(dataMap.containsKey(categoryId+""))
		{
			return dataMap.get(categoryId+"");
		}else 
		{
			SQLiteDatabase sqLiteDatabase = GreenNetOpenHelper.getSqlLiteDatabase();
			Cursor cursor = sqLiteDatabase.query(GuideDataTableConst.TABLE_NAME, null, GuideDataTableConst.CATEGORY_ID + "=?", new String[] { categoryId + "" }, null, null, null);

			ArrayList<GuideOnlineItemVo> cVos = new ArrayList<GuideOnlineItemVo>();
			while (cursor.moveToNext()) {
				GuideOnlineItemVo vo = new GuideOnlineItemVo();
				int id = cursor.getInt(cursor.getColumnIndex(GuideDataTableConst.TABLE_ID));
				int order = cursor.getInt(cursor.getColumnIndex(GuideDataTableConst.ORDERING));
				String picUrl = cursor.getString(cursor.getColumnIndex(GuideDataTableConst.PIC_URL));
				String infoTxt = cursor.getString(cursor.getColumnIndex(GuideDataTableConst.INFO_TXT));
				String webUrl = cursor.getString(cursor.getColumnIndex(GuideDataTableConst.WEB_URL));

				vo.id = id;
				vo.picUrl = picUrl;
				vo.infoString = infoTxt;
				vo.webUrl = webUrl;
				vo.ordering = order;
				vo.categoryId = categoryId;

				cVos.add(vo);

			}

			cursor.close();
			sqLiteDatabase.close();

			if(cVos.size() > 0)
			{
				dataMap.put(categoryId+"", cVos);
			}
			
			return cVos;
		}
		
		
	}

}
