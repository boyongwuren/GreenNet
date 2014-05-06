package com.nd.pad.vo.sqlDataBase;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nd.pad.constPackage.database.KeyCallTableConst;
import com.nd.pad.vo.KeyCallItemVo;

/**
 * 处理重点推荐的表格
 * 
 * @author zmp
 * 
 */
public class HandlerKeyCallTable
{

	/**
	 * 插入数据到重点推荐表
	 * 
	 * @param infoTxt
	 * @param picUrl
	 * @param webUrl
	 */
	public static void insertDataToTable(String infoTxt, String picUrl, String webUrl, int ordering)
	{
		insertDataToTable(infoTxt, picUrl, webUrl, ordering, true);
	}
	

	
	/**
	 * 插入数据到重点推荐表
	 * 
	 * @param infoTxt
	 * @param picUrl
	 * @param webUrl
	 */
	public static void insertDataToTable(String infoTxt, String picUrl, String webUrl, int ordering,boolean isClose)
	{
		SQLiteDatabase sqLiteDatabase = GreenNetOpenHelper.getInstance().getReadableDatabase();
		
		Cursor cursor = sqLiteDatabase.query(KeyCallTableConst.TABLE_NAME, new String[] { KeyCallTableConst.WEB_URL }, KeyCallTableConst.WEB_URL + "=?", new String[] { webUrl }, null, null, null);
		
		if (cursor.moveToNext()) {
			// 有这条记录
			
		} else {
			// 没有这条记录
			// 插入
			ContentValues cv = new ContentValues();
			cv.put(KeyCallTableConst.INFO_TXT, infoTxt);
			cv.put(KeyCallTableConst.PIC_URL, picUrl);
			cv.put(KeyCallTableConst.WEB_URL, webUrl);
			cv.put(KeyCallTableConst.ORDERING, ordering);
			
			sqLiteDatabase.insert(KeyCallTableConst.TABLE_NAME, null, cv);
		}
		
		if(isClose)
		{
			sqLiteDatabase.close();
		}
	}
	
	
	
	
	public static void insertDataToTable(KeyCallItemVo vo)
	{
		insertDataToTable(vo, true);
	}

	public static void insertDataToTable(KeyCallItemVo vo,boolean isClose)
	{
		insertDataToTable(vo.infoString, vo.picUrl, vo.webUrl, vo.ordering,isClose);
	}
	
	public static void insertDataToTable(ArrayList<KeyCallItemVo> kVos)
	{
		for (int i = 0; i < kVos.size(); i++) 
		{
			insertDataToTable(kVos.get(i),false);
		}
		
		SQLiteDatabase sqLiteDatabase = GreenNetOpenHelper.getInstance().getReadableDatabase();
		sqLiteDatabase.close();
	}

	/**
	 * 获取重点推荐的数据
	 * 
	 * @return
	 */
	public static ArrayList<KeyCallItemVo> getDataFromTable()
	{
		SQLiteDatabase sqLiteDatabase = GreenNetOpenHelper.getSqlLiteDatabase();
		Cursor cursor = sqLiteDatabase.query(KeyCallTableConst.TABLE_NAME, null, null, null, null, null, null);

		ArrayList<KeyCallItemVo> cVos = new ArrayList<KeyCallItemVo>();
		while (cursor.moveToNext()) {
			KeyCallItemVo vo = new KeyCallItemVo();
			int id = cursor.getInt(cursor.getColumnIndex(KeyCallTableConst.TABLE_ID));
			int ordering = cursor.getInt(cursor.getColumnIndex(KeyCallTableConst.ORDERING));
			String picUrl = cursor.getString(cursor.getColumnIndex(KeyCallTableConst.PIC_URL));
			String infoTxt = cursor.getString(cursor.getColumnIndex(KeyCallTableConst.INFO_TXT));
			String webUrl = cursor.getString(cursor.getColumnIndex(KeyCallTableConst.WEB_URL));
			vo.id = id;
			vo.picUrl = picUrl;
			vo.infoString = infoTxt;
			vo.webUrl = webUrl;
			vo.ordering = ordering;

			cVos.add(vo);

		}

		cursor.close();
		sqLiteDatabase.close();

		return cVos;
	}
}
