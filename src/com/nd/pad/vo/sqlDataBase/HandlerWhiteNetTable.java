package com.nd.pad.vo.sqlDataBase;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nd.pad.constPackage.database.WhiteNetConst;

/**
 * 操作白名单表
 * @author zmp
 *
 */
public class HandlerWhiteNetTable
{
   /**
    * 插入数据到白名单
	* @param webUrl
	*/
	public static void insertDataToTable(String webUrl)
	{
	    ContentValues cv = new ContentValues();
		cv.put(WhiteNetConst.WEB_URL, webUrl);
		
		SQLiteDatabase sqLiteDatabase = GreenNetOpenHelper.getInstance().getReadableDatabase();
		
		sqLiteDatabase.insert(WhiteNetConst.TABLE_NAME, null, cv);
		
		sqLiteDatabase.close();
	}

	/**
	 * 插入数据到白名单
	 * @param webUrl
	 */
	public static void insertDataToTable(ArrayList<String> whites)
	{
		for (int i = 0; i < whites.size(); i++) 
		{
			insertDataToTable(whites.get(i));
		}
	}
   
   /**
    * 从白名单获取数据
	* @return
	*/
	public static ArrayList<String> getDataFromTable()
   {
	   SQLiteDatabase sqLiteDatabase = GreenNetOpenHelper.getSqlLiteDatabase();
       Cursor cursor = sqLiteDatabase.query(WhiteNetConst.TABLE_NAME, null, null, null, null, null, null);
       
       ArrayList<String> cVos = new ArrayList<String>();
       while (cursor.moveToNext()) 
       {
			 String webUrl = cursor.getString(cursor.getColumnIndex(WhiteNetConst.WEB_URL));
			 cVos.add(webUrl);
		}
       
       cursor.close();
       sqLiteDatabase.close();
       
       return cVos;
   }
	 
}
